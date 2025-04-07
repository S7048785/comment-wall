<template>
  <div class="foot-bar">
    <!-- <div class="foot-bar-top">已经到底了</div> -->
    <div class="foot-bar-content"></div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
</script>

<style lang="less" scoped>
.foot-bar {
  .foot-bar-top {
    width: 100%;
    height: 50px;
    text-align: center;
    line-height: 50px;
  }
  .foot-bar-content {
    width: 100%;
    height: 200px;
    background-color: @gray-0;
  }
}
</style>
