<template>
  <ProductList :productListData="wishListData" />
</template>

<script>
import ProductList from "../components/ProductList";
import { mapState } from "vuex";

export default {
  name: "MyWish",
  data() {
    return {
      wishListData: Array,
    };
  },
  components: {
    ProductList,
  },
  computed: {
    ...mapState(["userInfo", "productListData"]),
  },
  mounted() {
    let wishIds = this.userInfo.liked;

    if (wishIds.length !== 0 && this.productListData !== null) {
      this.wishListData = this.productListData.filter((item) =>
        wishIds.includes(item.id)
      );
    } else {
      this.wishListData = null;
    }
  },
};
</script>
