<template>
  <div class="wall-message">
    <div class="title">留言墙</div>
    <div class="slogan">很多事情值得记录，当然也值得回味</div>
    <div class="label-list">
      <el-radio-group v-model="label" size="large">
        <el-radio-button
          v-for="item in labelList"
          :key="item"
          :value="item"
          :label="item"
        />
      </el-radio-group>
    </div>
    <div class="card">
      <NodeCard
        class="card-item"
        v-for="(item, index) in cardList"
        :key="item.id"
        :card="item"
        :bgcolor="cardColorList[index % cardColorList.length]"
      />
    </div>
    <div class="add" ref="add">
      <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-xinzeng"></use>
      </svg>
    </div>
  </div>
</template>

<script setup lang="ts">
import NodeCard from "@/components/NodeCard.vue";
import { ref, reactive, onMounted, onBeforeUnmount } from "vue";
import { useCategoryStore } from "@/stores/category";
import { type Card } from "@/types/interface/card";
import { Label } from "@/types/enum/label";
// 标签列表
const labelList: string[] = useCategoryStore().labelList;
// 标签：'全部'
const label = ref(labelList[0]);
// 卡片列表
const cardList = reactive<Card[]>([
  {
    id: "26146514651",
    date: new Date(),
    content: "路上总有阴影，但你抬头就能看到光。",
    username: "用户名1",
    liked: true,
    likeCount: 3,
    commentCount: 5,
    category: Label.OTHER,
  },
  {
    id: "26146514652",
    date: new Date(),
    content: "你如果不想被坑，就不要先进坑。",
    username: "用户名2",
    liked: false,
    likeCount: 1,
    commentCount: 2,
    category: Label.LOVE,
  },
  {
    id: "26146514653",
    date: new Date(),
    content:
      "我曾听人说过，当你不能够再拥有，你唯一可以做的，就是令自己不要忘记。 -- 《东邪西毒》",
    username: "用户名3",
    liked: true,
    likeCount: 5,
    commentCount: 3,
    category: Label.IDEAL,
  },
  {
    id: "26146514654",
    date: new Date(),
    content: "我在最好的时候碰到你，是我的运气。",
    username: "用户名4",
    liked: false,
    likeCount: 0,
    commentCount: 0,
    category: Label.PAST,
  },
  {
    id: "26146514655",
    date: new Date(),
    content:
      "当你年轻时，以为什么都有答案，不过老了的时候，你可能又觉得其实人生并没有所谓的答案。 -- 《堕落天使》",
    username: "用户名5",
    liked: true,
    likeCount: 7,
    commentCount: 4,
    category: Label.FUTURE,
  },
  {
    id: "26146514656",
    date: new Date(),
    content:
      "最先感知到三十将至的也许不是我们的身体，也不是精神，而是物质。 -- 《三十而已》",
    username: "用户名6",
    liked: false,
    likeCount: 2,
    commentCount: 1,
    category: Label.LOVELETTER,
  },
  {
    id: "26146514657",
    date: new Date(),
    content:
      "可能在一生中，很难找到个你肯爱他的人。如果找到的话，无论情况是怎样，都应该去尝试，我害怕将来后悔。 -- 《志明与春娇》",
    username: "用户名7",
    liked: true,
    likeCount: 4,
    commentCount: 2,
    category: Label.FRIEND,
  },
  {
    id: "26146514658",
    date: new Date(),
    content: "人要么好看，要么丑，夹在中间的只能说可爱。  -- 《年轻气盛》",
    username: "用户名8",
    liked: false,
    likeCount: 1,
    commentCount: 0,
    category: Label.SECRET,
  },
  {
    id: "26146514659",
    date: new Date(),
    content: "情不知所起，一往而深。 -- 《游园惊梦》",
    username: "用户名9",
    liked: true,
    likeCount: 6,
    commentCount: 3,
    category: Label.HEARTBEAT,
  },
  {
    id: "26146514660",
    date: new Date(),
    content: "生活就是不断前行。",
    username: "用户名10",
    liked: false,
    likeCount: 0,
    commentCount: 0,
    category: Label.ALL,
  },
  {
    id: "26146514661",
    date: new Date(),
    content:
      "决定我们成为什么样的人的，不是我们的能力，而是我们的选择。 -- 《哈利·波特与密室》",
    username: "用户名11",
    liked: true,
    likeCount: 8,
    commentCount: 5,
    category: Label.TARGET,
  },
  {
    id: "26146514662",
    date: new Date(),
    content: "痛苦和恐惧不是死亡，还有挽回的余地。 -- 《教父》",
    username: "用户名12",
    liked: false,
    likeCount: 3,
    commentCount: 2,
    category: Label.MESSAGE,
  },
]);
// 卡片背景颜色列表
const cardColorList = reactive<string[]>([
  "#fcafa24d",
  "#93ff0579",
  "#bfefff",
  "#88c1f4c1",
  "##f7a4d3e1",
  "#ffef85",
  "#adffde89",
  "#f2dffad0",
]);
const add = ref<HTMLDivElement>();

// 监听滚动条，动态调整add的位置
function noteHeight() {
  // 200为底部栏高度
  if (scrollY + innerHeight + 200 >= document.body.scrollHeight) {
    (add.value as HTMLDivElement).style.bottom =
      scrollY + innerHeight + 200 - document.body.scrollHeight + 30 + "px";
  }
}
onMounted(() => {
  window.addEventListener("scroll", noteHeight);
});
onBeforeUnmount(() => {
  window.removeEventListener("scroll", noteHeight);
});
</script>

<style lang="less" scoped>
.wall-message {
  // Your styles here
  min-height: 700px;
  padding-top: 52px;
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

    .el-radio-button {
      padding: 0 5px;

      :deep(.el-radio-button__inner) {
        width: 70px;
        box-sizing: border-box;
        // transition: none;
        border: 1px solid transparent;
        background: none;
        color: @gray-1;
        // border-radius: 25px;
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
    width: 90%;
    flex-wrap: wrap;
    margin: auto;
    margin-block: 20px;
    display: flex;
    justify-content: center;
    align-items: baseline;
    .card-item {
      margin: 10px;
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
