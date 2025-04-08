<template>
  <transition name="modal-fade" v-show="isModalOpen">
    <div
      :style="{ top: `${top}%`, height: `${100 - top}vh` }"
      class="comment-modal"
      ref="index"
      @mousedown.stop="$event != commentArea ? (commentFootShow = false) : ''"
    >
      <div class="modal-head">
        <div class="modal-name">
          写{{ currentOption.id === "" ? "留言" : "评论" }}
        </div>

        <svg class="icon" aria-hidden="true" @click="close">
          <use xlink:href="#icon-guanbi"></use>
        </svg>
      </div>
      <!-- MessageCard -->
      <div id="msg-card" v-show="card.type === 'msg'">
        <div class="new-card">
          <div class="colors">
            <div class="color-li" v-show="currentOption.id === ''">
              <el-radio-group
                v-model="(<MsgCard>currentOption).color"
                size="large"
              >
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
            :style="{ backgroundColor: (currentOption as MsgCard).color }"
          >
            <div class="card-top">
              <div class="card-date">{{ currentOption.date }}</div>
              <div class="card-label">{{ currentOption.label }}</div>
            </div>
            <el-input
              type="textarea"
              v-model="(currentOption as MsgCard).content"
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
              :class="{ 'is-focus': nameInput && currentOption.id === '' }"
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
                v-for="(label, index) in msgLabel.slice(1)"
                :key="label"
                :value="label"
                :label="label"
                :disabled="currentOption.id !== ''"
              ></el-radio-button>
            </el-radio-group>
          </div>
        </div>
      </div>
      <div class="comment" v-show="currentOption.id !== ''">
        <div class="modal-head">
          <p class="modal-name">
            评论<span> {{ currentOption.commentCount }}</span>
          </p>
        </div>
        <div
          class="comment-area"
          ref="commentArea"
          @mousedown.stop
          @keydown="enterComment"
          v-click-outside="() => (commentFootShow = false)"
        >
          <div class="body">
            <el-input
              @focus="commentFootShow = true"
              v-model="commentMsg"
              ref="commentInput"
            />
          </div>
          <div class="footer" v-show="commentMsg.trim() || commentFootShow">
            <el-button round @click="comment(card.id)">发送</el-button>
          </div>
        </div>
      </div>
      <div class="contents" v-show="currentOption.id !== ''">
        <CommentContent
          :comments="commentStore.commentList"
          v-infinite-scroll="updateComment"
          :infinite-scroll-disabled="commentStore.isNone"
          :infinite-scroll-immediate="false"
        />
      </div>
      <div class="comment-btn" v-show="currentOption.id === ''">
        <el-button class="discard" round @click="discard">丢弃</el-button>
        <el-button class="publish" round @click="publish">发布</el-button>
      </div>
    </div>
  </transition>
</template>

<script setup lang="ts">
import { nextTick, onMounted, onUnmounted, ref, watch } from "vue";
import emitter from "@/utils/emitter";
import { type ImgCard, type MsgCard } from "@/types/interface/card";
import { cardColorList, msgLabel, cardNormal } from "@/utils/data";
import { useCardStore } from "@/stores/cardStore";
import { useCommentStore } from "@/stores/commentStore";
import CommentContent from "./CommentContent.vue";
import { ElMessage } from "element-plus";
import { type UserComment } from "@/types/interface/user";

const cardStore = useCardStore();
const commentStore = useCommentStore();
const isModalOpen = defineModel();
const { card, top = 0 } = defineProps<{
  card: ImgCard | MsgCard;
  top?: number;
}>();

const emit = defineEmits<{
  comment: [cardId: string];
  publish: [card: ImgCard | MsgCard];
}>();

// 更新评论
const updateComment = () => {
  console.log(commentStore.isNone);

  commentStore.getCommentList(
    currentOption.value.id,
    currentOption.value.type === "msg" ? 1 : 2
  );
};

// 当前卡片数据
const currentOption: any = ref({ ...card });
// 监听父组件传递的卡片数据变化，更新当前卡片数据
watch(
  () => card,
  (newVal, oldVal) => {
    currentOption.value = { ...newVal };
    if (newVal.id !== oldVal.id) {
      // 刷新评论区
      commentStore.getCommentList(
        newVal.id,
        newVal.type === "msg" ? 1 : 2,
        true
      );
    }
  }
);
// 监听当前卡片数据变化，更新store
watch(
  () => currentOption.value,
  (newVal, oldVal) => {
    // id 为空时，表明是新建留言
    if (newVal.id === "") {
      if (newVal.type === "msg") {
        cardStore.setCurrentMsgCard({ ...newVal });
      }
    } else {
    }
    if (newVal.type === "img") {
      cardStore.setCurrentImgCard({ ...newVal });
    }
    if (newVal.id !== oldVal.id) {
      // 数据变化时，跳转到顶部
      window.setTimeout(() => {
        index.value.scrollTo({
          top: 0,
          behavior: "smooth",
        });
      });
    }
  },
  {
    deep: true,
  }
);
const index = ref();
// 标记签名输入框 是否聚焦
const nameInput = ref(false);
// 是否显示评论区
const commentFootShow = ref(false);
const commentArea = ref();
// 评论输入框
const commentInput = ref();

// 关闭弹窗
function close() {
  emitter.emit("modal-toggle");
}
// 丢弃
function discard() {
  currentOption.value = { ...cardNormal };
  close();
}
// 发布留言
function publish() {
  // 校验留言板是否为空值
  const temp = currentOption.value as MsgCard;

  if (temp.content === "" || temp.username === "") {
    ElMessage({
      message: "留言内容或签名不能为空",
      type: "error",
      plain: true,
    });
    return;
  }
  // 校验留言板是否为当前用户所属
  if (currentOption.value.id !== "") {
    ElMessage({
      message: "当前留言非您所属 无法发布",
      type: "error",
      plain: true,
    });
    ElMessage.error("");
    return;
  }

  // 发布留言 请求
  cardStore.addCardMsg(temp);
  (currentOption.value.content as string).replace(/\n/g, "<br>");
  // 善后操作
  ElMessage({
    message: "发布成功",
    type: "success",
    plain: true,
  });
  discard();
}

// 留言评论
const commentMsg = ref("");
function enterComment(event: KeyboardEvent) {
  if (event.key === "Enter") {
    event.preventDefault();
    comment(card.id);
  }
}
// 发送评论
function comment(cardId: string) {
  // 判断空值
  const msg = commentMsg.value.trim();
  if (msg === "") {
    ElMessage({
      message: "评论内容不能为空",
      type: "error",
      plain: true,
    });
    return;
  }

  // TODO: 发送评论请求 返回用户当前评论
  commentStore.addComment(cardId, msg, 1);

  // 善后操作
  ElMessage({
    message: "评论成功",
    type: "success",
    plain: true,
  });
  currentOption.value.commentCount++;
  commentMsg.value = "";
  // 评论数+1
  emit("comment", cardId);
}

onMounted(() => {
  emitter.on("commentFocus", (card) => {
    emitter.emit("modal-toggle", card);
    nextTick(() => {
      commentInput.value.focus();
    });
  });
  // currentOption.value = { ...card };
});
onUnmounted(() => {
  emitter.off("commentFocus");
});
</script>

<style lang="less" scoped>
.comment-modal {
  width: 400px;
  height: 100vh;
  position: fixed;
  right: 0px;
  top: 0px;
  z-index: 102;
  background-color: #ffffffcc;
  box-shadow: 0px 0px 20px 0px #00000014;
  backdrop-filter: blur(10px);
  padding: 20px;
  overflow: auto;
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
      margin-top: 10px;
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
.modal-fade-enter-active {
  /*from*/
  animation: ani 0.3s;
}
.modal-fade-leave-active {
  /*to*/
  animation: ani 0.3s reverse;
}

/*动画样式*/
@keyframes ani {
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(0);
  }
}
</style>
