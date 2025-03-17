<script setup lang="ts">
import { RouterView } from "vue-router";
import TopBar from "@/components/TopBar.vue";
import FootBar from "@/components/FootBar.vue";
</script>

<template>
  <div>
    <RouterView />
  </div>
</template>

<style lang="less">
.icon {
  width: 1.5em;
  height: 1.5em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
