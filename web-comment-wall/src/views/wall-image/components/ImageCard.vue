<template>
  <div class="image-card">
    <img :src="src" alt="" />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, defineProps } from "vue";
const props = defineProps<{ src: string }>();
</script>

<style lang="less" scoped></style>
