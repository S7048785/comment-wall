<template>
  <div class="fixed-bar">
    <TopBar />
  </div>
</template>

<script setup lang="ts">
import { useScroll } from "@vueuse/core";
import TopBar from "@/components/TopBar.vue";

const { y } = useScroll(window);
</script>

<style lang="less" scoped>
.fixed-bar {
  // display: none;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999;
  &.show {
    display: block;
  }
}
</style>
