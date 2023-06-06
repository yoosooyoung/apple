<template>
  <div>
    <h1 class="visually-hidden">상품상세 페이지</h1>

    <ProductCarousel
      :data="data"
      :productImgModalIsShow="productImgModalIsShow"
      @openModal="openModal"
    />

    <UserInfo
      :userImage="`${data.userImage}`"
      :userId="`${data.userId}`"
      :userNickName="`${data.userNickName}`"
      :userLocation="`${data.userLocation}`"
    />

    <ProductInfo :data="data" />

    <ProductImgModal
      :data="data"
      :productImgModalIsShow="productImgModalIsShow"
      @closeModal="closeModal"
    />

    <ProductCta :data="data" />
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import UserInfo from "@/components/UserInfo.vue";
import ProductInfo from "@/components/ProductInfo.vue";
import ProductCta from "@/components/ProductCta.vue";
import ProductCarousel from "@/components/ProductCarousel.vue";
import ProductImgModal from "@/components/ProductImgModal.vue";
import { mapState } from "vuex";

export default {
  name: "ProductView",
  data() {
    return {
      data: {},
      productImgModalIsShow: false,
    };
  },
  components: {
    UserInfo,
    ProductInfo,
    ProductCta,
    ProductCarousel,
    ProductImgModal,
  },
  computed: {
    ...mapState(["productListData"]),
  },
  methods: {
    openModal() {
      this.productImgModalIsShow = true;
    },
    closeModal() {
      this.productImgModalIsShow = false;
    },
  },
  mounted() {
    // 사용할 데이터만 넘기기
    const route = useRoute();
    this.productListData.find((item, idx) => {
      if (item.id === Number(route.params.id)) {
        this.data = this.productListData[idx];
      }
    });
  },
};
</script>
