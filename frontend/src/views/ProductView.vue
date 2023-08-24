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
import axios from "axios";

export default {
  name: "ProductView",
  data() {
    return {
      data: {},
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

    axios.get(`/api/board/view/${this.board_seq}`)
        .then(response => {
          console.log("success:");
        })
        .catch(error => {
          console.error("Error fetching product:", error);
        });
  },
};
</script>
