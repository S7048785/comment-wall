import { useIntersectionObserver } from "@vueuse/core";

// 点击外部 隐藏元素
export const clickOutside = {
  install(app: any) {
    app.directive("click-outside", {
      mounted(el: any, binding: any) {
        // 给元素绑定一个事件
        el.EventLis = function (event: any) {
          if (!el.contains(event.target)) {
            // 如果点击的元素不在目标元素内部，则执行绑定的函数
            // console.log(el, event.target);
            // event.stopPropagation();
            binding.value();
          }
        };
        // 在全局添加点击事件监听
        document.addEventListener("mouseup", el.EventLis);
      },
      unmounted(el: any, binding: any) {
        // 在全局移除点击事件监听

        document.removeEventListener("mouseup", el.EventLis);
      },
    });
  },
};

// 懒加载
export const lazyPlugin = {
  install(app: any) {
    app.directive("lazy", {
      beforeUpdate: (el: any, binding: any) => {
        const { stop } = useIntersectionObserver(el, ([event]) => {
          if (event.isIntersecting) {
            stop(); // 停止监听
            // binding.value();
            console.log(el);
          }
        });
      },
    });
  },
};
