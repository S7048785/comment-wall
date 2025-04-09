<script setup lang="ts">
import { RouterView } from "vue-router";
import TopBar from "@/components/TopBar.vue";
import FootBar from "@/components/FootBar.vue";
import HomeView from "./views/HomeView.vue";
</script>

<template>
  <HomeView />
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
