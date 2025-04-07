<template>
  <div class="wall-image">
    <div class="title">图片墙</div>
    <div class="slogan">很多事情值得记录，当然也值得回味</div>
    <div class="label-list">
      <el-radio-group v-model="currentLabel" size="large">
        <el-radio-button
          v-for="item in imgLabel"
          :key="item"
          :value="item"
          :label="item"
        />
      </el-radio-group>
    </div>
    <div class="card">
      <!-- 图像卡片-->
      <div class="container">
        <div
          class="item"
          @mouseup.left.stop="ViewToggle(item)"
          v-for="(item, index) in imageCardListReactive"
          :key="item.id"
        >
          <img :src="item.url" alt="" />
          <div class="bg">
            <div class="liked" @mouseup.stop @click="toggleLiked(item)">
              <Heart v-model="item.liked" />
              <span class="count">{{ item.likeCount }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <ImageViewer
      v-show="showViewOpen"
      :card="targetCard"
      @like="toggleLiked"
      @viewToggle="ViewToggle"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onUnmounted, watch } from "vue";
import emitter from "@/utils/emitter";
import ImageViewer from "./components/ImageViewer.vue";
import { type ImgCard } from "@/types/interface/card";
import { imgLabel, imageCardList } from "@/utils/data";
import { useCardStore } from "@/stores/cardStore";
const imageCardListReactive = reactive<ImgCard[]>(imageCardList);
const currentLabel = ref(imgLabel[0]);

// 点击喜欢按钮时触发的事件
async function toggleLiked(imageCard: ImgCard) {
  imageCardListReactive.find((item: ImgCard) => {
    if (item.id === imageCard.id) {
      item.liked = !item.liked;
      item.likeCount += item.liked === true ? 1 : -1;
      imageCard.liked = item.liked;
      imageCard.likeCount = item.likeCount;
      // console.log(imageCard);

      // TODO: 发送喜欢事件
    }
  });
}

const add = ref<HTMLDivElement>();
// 监听滚动条，动态调整add的位置
// function noteHeight() {
//   // 200为底部栏高度
//   if (scrollY + innerHeight + 200 >= document.body.scrollHeight) {
//     (add.value as HTMLDivElement).style.bottom =
//       scrollY + innerHeight + 200 - document.body.scrollHeight + 50 + "px";
//   } else {
//     (add.value as HTMLDivElement).style.bottom = "50px";
//   }
// }

let targetCard = ref<ImgCard>({
  ...(useCardStore().currentImgCard as ImgCard),
});

// 预览图状态
const showViewOpen = ref(false);
// 监听是否打开预览图，防止滚动穿透
watch(showViewOpen, (newVal) => {
  if (newVal === true) {
    document.body.style.overflow = "hidden";
  } else {
    document.body.style.overflow = "auto";
  }
});
/**
 * 打开预览视图
 */
function ViewToggle(card?: ImgCard) {
  // 为null时表示点击了卡片以外的区域，所以关闭弹窗
  if (card == null || showViewOpen.value === true) {
    showViewOpen.value = false;
    targetCard.value.url = "";
    targetCard.value.id = "";

    // window.setTimeout(() => {
    //   targetCard.value.id = "";
    // }, 300);
    return;
  }
  // 打开当前卡片弹窗
  showViewOpen.value = true;
  if (Object.keys(card).length === 0) {
    // 若card为空对象，表示为新增卡片，则使用默认数据
    targetCard.value = useCardStore().currentImgCard;
  } else {
    targetCard.value = { ...card };
  }
}

onMounted(() => {
  // window.addEventListener("scroll", noteHeight);
  // 绑定弹窗切换事件
});
onUnmounted(() => {
  // window.removeEventListener("scroll", noteHeight);
  document.body.style.overflow = "auto";
});
</script>

<style lang="less" scoped>
.wall-image {
  // Your styles here
  min-height: 700px;
  padding-top: 52px;
  &.handlerScroll {
    overflow: hidden;
  }
  .title {
    padding-top: 8px;
    padding-bottom: 8px;
    font-size: 56px;
    color: @gray-0;
    text-align: center;
    font-weight: bold;
  }
  .slogan {
    color: @gray-1;
    text-align: center;
  }
  .label-list {
    display: flex;
    justify-content: center;
    margin-top: 48px;
    .el-radio-group {
      justify-content: center;
    }
    .el-radio-button {
      padding: 0 5px;

      :deep(.el-radio-button__inner) {
        width: 70px;
        box-sizing: border-box;
        border: 1px solid transparent;
        background: none;
        color: @gray-1;
        &:hover {
          border-color: #ccc;
          border-radius: 25px;
          color: #000;
        }
      }
      &.is-active {
        :deep(.el-radio-button__inner) {
          color: @gray-0;
          font-weight: 600;
          box-shadow: none;
          border: 1px solid;
          border-radius: 25px;
        }
      }
    }
  }
  .card {
    display: flex;
    justify-content: center;
    margin-block: 20px;
    // 瀑布流布局
    .container {
      width: 80%;
      column-count: 5;
      column-gap: 20px;
      .item {
        break-inside: avoid;
        margin-bottom: 20px;
        border-radius: 8px;
        width: 260px;
        overflow: hidden;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        position: relative;
        &:hover {
          .bg {
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: #5a5a5a17;
          }
        }

        img {
          width: 100%;
          height: auto;
          display: block;
        }
        .bg {
          display: none;
          .liked {
            position: absolute;
            cursor: pointer;
            right: 15px;
            bottom: 8px;
            width: 70px;
            display: flex;
            background-color: #cccccc96;
            border-radius: 15px;
            justify-content: space-evenly;
            .heart-container {
              width: 20px;
              height: 20px;
              :deep(.svg-container) {
                margin-top: 2px;
              }
            }
            .count {
              font-size: 16px;
              font-weight: 600;
              user-select: none;
            }
          }
        }
      }
    }
    @media (max-width: 1700px) {
      .container {
        column-count: 4;
      }
    }

    @media (max-width: 1385px) {
      .container {
        column-count: 3;
      }
    }

    @media (max-width: 1045px) {
      .container {
        column-count: 2;
      }
    }

    @media (max-width: 700px) {
      .container {
        display: flex;
        flex-direction: column;
        align-items: center;
      }
    }
  }
  .add {
    position: fixed;
    right: 50px;
    bottom: 50px;
    color: #ccc;
    border-radius: 50%;
    outline: 5px solid;
    font-size: 2em;
    height: 42px;
    cursor: pointer;
    transition: transform 0.5s ease;
    &:hover {
      background-color: #000;
      transform: rotate(180deg);
    }
  }
}
</style>
