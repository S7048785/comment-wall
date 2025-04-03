<template>
  <div class="comment-content">
    <div class="comment-content-box" v-for="(item, index) in userComments">
      <div class="left">
      <div class="img" :style="`background-image: url('${item.img}')`"></div>
      </div>
      <div class="right">
        <div class="name"><span>{{ item.name }}</span></div>
        <div class="content">{{ item.content }}</div>
        <div class="date">{{ item.date }}</div>
      </div>
    </div>  
  </div>
</template>

<script setup lang="ts">
import { userComments } from '@/utils/data';
</script>

<style scoped lang="less">
.comment-content {
  display: flex;
  flex-wrap: wrap;
  .comment-content-box {
    position: relative;
    // margin-bottom: 20px;
    &::after {
      display: block;
      content: '';
      width: 100%;
      height: 1px;
      margin-block: 15px;
      background-color: #ccc;
    }
    &:last-child {
      margin-bottom: 20px;
      &::after {
        display: none;
      }
    }
    .left {
      position: absolute;

      padding-top: 10px;

      .img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
      }
    }
    .right {
      padding-left: 60px;
      // padding-top: 5px;
      .name {
        height: 30px;
        display: flex;
        color: #aaa;
        align-items: center;
      }
      .content {

      }
      .date {
        text-align: right;
        margin-top: 5px;
        font-size: 12px;
        color: #aaa;
      }
    }
  }
  
}
</style>