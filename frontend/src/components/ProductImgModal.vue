<template>
  <div class="product-img-modal" v-if="productImgModalIsShow">
    <div class="modal-bg" @click="$emit('closeModal')"></div>

    <div class="modal">
      <button
        type="button"
        class="button-close"
        aria-label="팝업 닫기"
        @click="$emit('closeModal')"
      >
        <i class="xi-close"></i>
      </button>

      <swiper
        :modules="modules"
        :slides-per-view="1"
        :auto-height="true"
        :pagination="{ clickable: true }"
        @swiper="onSwiper"
        @slideChange="onSlideChange"
      >
        <swiper-slide v-for="(item, idx) in data.productImages" :key="idx"
          ><img :src="`${item}`" alt=""
        /></swiper-slide>
      </swiper>
    </div>
  </div>
</template>

<script>
import { Pagination } from "swiper";
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";

export default {
  name: "ProductImgPopUp",
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {
    data: Object,
    productImgModalIsShow: Boolean,
  },
  setup() {
    const onSwiper = () => {};
    const onSlideChange = () => {};
    return {
      onSwiper,
      onSlideChange,
      modules: [Pagination],
    };
  },
};
</script>
