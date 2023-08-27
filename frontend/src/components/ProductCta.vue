<template>
  <div class="product-cta">
    <div class="left">
      <button
        class="button-wish"
        aria-label="위시리스트에 추가"
        @click="toggleWish(Number($route.params.id))"
      >
        <!-- BACK TO DO : 좋아요 개발 필요 - 230827 JHJ-->
        <!-- FRONT TO DO : 유저 좋아요 데이터 추가시 다시 작업하기 - 230827 JHJ
        v-if="userInfo.liked.includes(Number($route.params.id)) == false" -->
        <i class="xi-heart-o" aria-hidden="true"></i>
        <!-- <i v-else class="xi-heart" aria-hidden="true"></i> -->
      </button>
      <div class="divider" aria-hidden="true"></div>
      <strong class="price"
        >{{ Number(productData.BOARD_PRICE).toLocaleString() }}원</strong
      >
    </div>

    <button
      class="button-chat h38 button-primary"
      aria-label="채팅하기 버튼"
      @click="$router.push('/chatView')"
    >
      채팅하기
    </button>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  name: "ProductCta",
  data() {
    return {};
  },
  props: {
    productData: Object,
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  methods: {
    ...mapMutations(["toggleWish"]),
  },
};
</script>
