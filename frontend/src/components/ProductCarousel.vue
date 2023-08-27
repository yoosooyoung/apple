<template>
  <div class="product-carousel">
    <swiper
      :modules="modules"
      :slides-per-view="1"
      :pagination="{ clickable: true }"
      @swiper="onSwiper"
      @slideChange="onSlideChange"
    >
      <swiper-slide
        v-for="(item, idx) in productImages"
        :key="idx"
        @click="$emit('openModal')"
      >
        <!-- <img :src="require(`/upload/${item}`)" alt=""
      /> -->
      </swiper-slide>
    </swiper>
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
  name: "ProductCarousel",
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {
    productImages: Array,
    productImgModalIsShow: Boolean,
  },
  mounted() {
    console.log("22", this.productImages, this.productImgModalIsShow);
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
