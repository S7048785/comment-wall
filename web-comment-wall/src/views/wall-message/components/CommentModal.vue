<template>
  <div
    class="comment-modal"
    v-show="isModalOpen"
    v-click-outside="close"
    ref="index"
  >
    <div class="modal-head">
      <div class="modal-name">
        写{{ currentOption.id === "" ? "留言" : "评论" }}
      </div>

      <svg class="icon" aria-hidden="true" @click="close">
        <use xlink:href="#icon-guanbi"></use>
      </svg>
    </div>
    <!-- <NewCard /> -->
    <div class="new-card">
      <div class="colors">
        <div class="color-li" v-show="currentOption.id === ''">
          <el-radio-group v-model="currentOption.color" size="large">
            <el-radio-button
              v-for="(color, index) in cardColorList"
              :key="color"
              :style="{ backgroundColor: color }"
              :value="color"
              :disabled="currentOption.id !== ''"
              :class="{ disabled: currentOption.id !== '' }"
              size="small"
            />
          </el-radio-group>
        </div>
      </div>
      <div
        class="card-main"
        :class="{ 'is-user': currentOption.id === '' }"
        :style="{ backgroundColor: currentOption.color }"
      >
        <div class="card-top">
          <div class="card-date">{{ currentOption.date }}</div>
          <div class="card-label">{{ currentOption.label }}</div>
        </div>
        <el-input
          type="textarea"
          v-model="currentOption.message"
          placeholder="留言..."
          class="message"
          resize="none"
          show-word-limit
          :rows="8"
          maxlength="75"
          :readonly="currentOption.id !== ''"
          input-style="height: 100%"
        />
        <el-input
          placeholder="签名"
          class="name"
          :class="{ 'is-focus': nameInput }"
          @focus="nameInput = true"
          @blur="nameInput = false"
          :readonly="currentOption.id !== ''"
          v-model="currentOption.username"
          maxlength="10"
        />
      </div>
    </div>

    <div class="labels" v-show="currentOption.id === ''">
      <div class="modal-head">
        <p class="modal-name">选择标签</p>
      </div>
      <div class="label-li">
        <el-radio-group
          v-model="currentOption.label"
          :disabled="currentOption.id !== ''"
          size="large"
        >
          <el-radio-button
            v-for="(label, index) in cardLabelList.slice(1)"
            :key="label"
            :value="label"
            :label="label"
            :disabled="currentOption.id !== ''"
          ></el-radio-button>
        </el-radio-group>
      </div>
    </div>
    <div class="comment" v-show="currentOption.id !== ''">
      <div class="modal-head">
        <p class="modal-name">
          评论<span> {{ 123 }}</span>
        </p>
      </div>
      <div
        class="comment-area"
        v-click-outside="() => (commentFootShow = false)"
      >
        <div class="body">
          <el-input
            @focus="commentFootShow = true"
            v-model="commentMsg"
            ref="commentInput"
          />
        </div>
        <div class="footer" v-show="commentFootShow">
          <el-button round @click="comment">发送</el-button>
        </div>
      </div>
    </div>
    <div class="contents" v-show="currentOption.id !== ''">
      <CommentContent />
    </div>
    <div class="comment-btn" v-show="currentOption.id === ''">
      <el-button class="discard" round @click="discard">丢弃</el-button>
      <el-button class="publish" round @click="publish">发布</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref, watch } from "vue";
import emitter from "@/utils/emitter";
import { type Card } from "@/types/interface/card";
import { cardColorList, cardLabelList, cardNormal } from "@/utils/data";
import { useCardStore } from "@/stores/card";
import CommentContent from "./CommentContent.vue";
import { ElMessage } from "element-plus";
// 获取dom
const index = ref();
const cardStore = useCardStore();
const props = defineProps<{
  card: Card;
}>();

// 当前卡片数据
const currentOption = ref({ ...props.card });

const isModalOpen = ref(true);
// 监听父组件传递的卡片数据变化，更新当前卡片数据
watch(
  () => props.card,
  (newVal) => {
    currentOption.value = { ...newVal };
    // console.log(newVal);
  }
);
// 监听当前卡片数据变化，更新store
watch(
  () => currentOption.value,
  (newVal) => {
    if (newVal.id === "") {
      cardStore.setCurrentCard({ ...newVal });
    }
    // 数据变化时，跳转到顶部
    window.setTimeout(() => {
      index.value.scrollTo({
        top: 0,
        behavior: "smooth",
      });
    });
  },
  {
    deep: true,
  }
);
// 标记签名输入框 是否聚焦
const nameInput = ref(false);
// 是否显示评论区
const commentFootShow = ref(false);
// 评论输入框
const commentInput = ref();
onMounted(() => {
  emitter.on("commentFocus", () => {
    commentInput.value.focus();
  });
});
onUnmounted(() => {
  emitter.off("commentFocus");
});
// 关闭弹窗
function close() {
  emitter.emit("modal-toggle");
}
// 丢弃
function discard() {
  currentOption.value = { ...cardNormal };
  close();
}
// 发布
function publish() {
  // 校验留言板是否为空值
  const temp = currentOption.value;
  if (temp.message === "" || temp.username === "") {
    ElMessage.error("留言内容或签名不能为空");
    return;
  }
  // 校验留言板是否为当前用户所属
  if (currentOption.value.id !== "") {
    ElMessage.error("当前留言非您所属 无法发布");
    return;
  }

  // TODO: 发布留言 请求
}

// 留言评论
const commentMsg = ref("");
// 发送评论
function comment() {
  // 判断空值
  const msg = commentMsg.value.trim();
  if (msg === "") {
    ElMessage.error("评论内容不能为空");
    return;
  }

  // TODO: 发送评论 请求
}
</script>

<style lang="less" scoped>
.comment-modal {
  width: 400px;
  height: 94%;
  position: fixed;
  right: 0px;
  top: 6%;
  z-index: 1000;
  background-color: #ffffffcc;
  box-shadow: 0px 0px 20px 0px #00000014;
  backdrop-filter: blur(10px);
  padding: 20px;
  // padding-bottom: 50px;
  overflow-y: auto;
  .modal-head {
    display: flex;
    justify-content: space-between;
    .modal-name {
      font-size: 18px;
      color: @gray-1;
      font-weight: 600;
      line-height: 32px;
    }
    svg {
      color: green;
      cursor: pointer;
    }
    .modal-btn {
      .publish {
      }
    }
  }
  .new-card {
    padding: 0 20px;
    margin-bottom: 20px;
    .colors {
      .color-li {
        margin-block: 10px;
        .el-radio-group {
          width: 100%;
          justify-content: space-evenly;
        }
        .el-radio-button {
          &.is-active {
            :deep(.el-radio-button__inner) {
              color: none;
              font-weight: 600;
              box-shadow: none;
              border: 1px solid black;
            }
          }
          :deep(.el-radio-button__inner) {
            width: 100%;
            background: none;
            height: 24px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: none;
          }
        }
      }
      .color-selected {
        border: 1px solid rgba(59, 115, 240, 1);
      }
    }
    .card-main {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      height: 240px;
      width: 100%;
      background: rgba(252, 175, 162, 0.31);
      padding: 20px;
      margin-block: 10px;
      box-sizing: border-box;
      .card-top {
        display: flex;
        justify-content: space-between;
        color: #aaa;
        .card-date {
        }
        .card-label {
        }
      }
      .message {
        font-size: 16px;
        height: 75%;
        padding-block: 5px;
        // flex: 1;
      }
      .name {
        height: 20%;
        &.is-focus {
          :deep(.el-input__wrapper) {
            border: 2px solid #4a9dec;
            box-shadow: 0px 0px 0px 3px #4a9dec33;
            background-color: white;
          }
        }
      }
      :deep(.el-textarea__inner) {
        box-shadow: none;
        background-color: transparent;
      }
      :deep(.el-input__wrapper) {
        box-shadow: none;
        background-color: transparent;
      }
      &.is-user {
        :deep(.el-input__wrapper) {
          // outline: 1px solid #fff;
          border: 2px solid transparent;
          width: 15em;
          height: 2.5em;
          padding-left: 0.8em;
          outline: none;
          overflow: hidden;
          background-color: #f3f3f3;
          border-radius: 10px;
          transition: all 0.5s;
          &:hover {
            border: 2px solid #4a9dec;
            box-shadow: 0px 0px 0px 3px #4a9dec33;
            background-color: white;
          }
        }
      }
      :deep(.el-input__inner) {
        text-align: right;
        font-size: 16px;
      }
    }
  }
  .labels {
    .label-li {
      margin-block: 10px;
      .el-radio-group {
        justify-content: space-between;
      }
    }
  }

  .el-radio-button {
    margin: 2px;
    border-radius: 10px;

    &.is-active {
      :deep(.el-radio-button__inner) {
        font-weight: 600;
        color: black;
        box-shadow: none;
        background: #ccc;
        border-radius: 15px;
      }
    }
    :deep(.el-radio-button__inner) {
      width: 100%;
      background: none;
      border: none;
      &:hover {
        color: black;
      }
    }
  }
  .comment {
    .modal-head {
      .modal-name {
        span {
          font-size: 14px;
          margin-left: 5px;
        }
      }
    }
    .comment-area {
      margin-block: 10px;
      .footer {
        text-align: right;
        margin-top: 10px;
        padding-right: 10px;
        :deep(.el-button:hover) {
          background-color: #eee;
          border-color: #ddd;
          color: black;
          outline: none;
        }
      }
    }
  }
  .contents {
    margin-top: 20px;
  }
  .comment-btn {
    position: absolute;
    bottom: 25px;
    width: 90%;
    text-align: center;
    .el-button {
      width: 40%;
      height: 40px;
      &.discard:hover {
        background-color: #eee;
        border-color: #ddd;
        color: black;
        outline: none;
      }
      &.publish {
        background-color: #000;
        color: white;
        &:hover {
          background-color: #333;
        }
      }
    }
    :deep(.el-button:hover) {
      // background-color: #eee;
      // border-color: #ddd;
      // color: black;
      // outline: none;
    }
  }
}
</style>
