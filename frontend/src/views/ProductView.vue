<template>
  <div>
    <h1 class="visually-hidden">상품상세 페이지</h1>

    <ProductCarousel
      :productImages="productImages"
      :productImgModalIsShow="productImgModalIsShow"
      @openModal="openModal"
    />

    <!-- BACK TO DO : 글 작성한 유저 정보 필요 - 230827 JHJ-->
    <UserInfo
      :userImage="null"
      :userId="`testId`"
      :userNickName="`테스트닉네임`"
      :userLocation="`테스트위치`"
    />

    <ProductInfo :productData="productData" />

    <ProductImgModal
      :productImages="productImages"
      :productImgModalIsShow="productImgModalIsShow"
      @closeModal="closeModal"
    />

    <ProductCta :productData="productData" />
  </div>
</template>

<script>
import UserInfo from "@/components/UserInfo.vue";
import ProductInfo from "@/components/ProductInfo.vue";
import ProductCta from "@/components/ProductCta.vue";
import ProductCarousel from "@/components/ProductCarousel.vue";
import ProductImgModal from "@/components/ProductImgModal.vue";
import { mapState } from "vuex";
import { getOneBoardRequest } from "../apis/board";

export default {
  name: "ProductView",
  data() {
    return {
      productData: {},
      productImages: [],
      productImgModalIsShow: false,
      board_seq: null,
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
    this.board_seq = this.$route.params.id;

    getOneBoardRequest(this.board_seq)
      .then((response) => {
        this.productData = response.data.board;
        this.productImages = response.data.pictures;
      })
      .catch((error) => {
        console.error(error);
      });
  },
};
</script>
