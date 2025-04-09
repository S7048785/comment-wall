<template>
  <div
    class="form-control"
    :style="{
      width: `${width}px`,
    }"
  >
    <input
      :type="str"
      v-model="val"
      @focus="isFocus = true"
      @blur="blur"
      autocomplete="on"
    />
    <label :class="{ 'is-focus': isFocus || val }">
      <span
        v-for="(item, index) in str"
        :style="{ 'transition-delay': `${index * 50}ms` }"
        >{{ item }}</span
      >
    </label>
    <div v-show="!error && val" class="errorMsg" v-html="errorMsg"></div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import { isUserName } from "@/utils/regex";
const { width = 200, str = "Username" } = defineProps<{
  width?: number;
  str?: string;
  errorMsg: string;
}>();
const emit = defineEmits<{
  validate: [boolean];
}>();
const val: any = defineModel<string>();
const type = ref(str === "Password" ? "password" : "text");
const isFocus = ref(false);

const error = ref(false);
// 失去聚焦
function blur() {
  isFocus.value = false;
  // 校验字符串
  const isTrue = isUserName(val.value);
  emit("validate", isTrue);
  error.value = isTrue;
}
</script>

<style lang="less" scoped>
.form-control {
  position: relative;
  // margin: 20px 0 40px;
  width: 190px;
  input {
    background-color: transparent;
    border: 0;
    border-bottom: 2px #fff solid;
    display: block;
    width: 100%;
    height: 40px;
    font-size: 18px;
    color: #000000;
    &:focus,
    &:valid {
      outline: 0;
      border-bottom-color: #b4b4b4;
    }
  }
  label {
    position: absolute;
    bottom: 0px;

    left: 0;
    pointer-events: none;
    &.is-focus {
      span {
        color: #b6e4fa;
        transform: translateY(-30px);
      }
    }
    span {
      display: inline-block;
      font-size: 18px;
      min-width: 5px;
      color: #b7b7b7;
      transition: 0.3s cubic-bezier(0.68, -0.55, 0.265, 1.55);
    }
  }
  .errorMsg {
    position: relative;
    left: 0;
    color: red;
    font-size: 10px;
  }
}
</style>
