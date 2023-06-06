<template>
  <ProductList :productListData="myPostData" />
</template>

<script>
import { mapState } from "vuex";
import ProductList from "../components/ProductList";

export default {
  name: "MyWish",
  data() {
    return {
      myPostData: null,
    };
  },
  components: {
    ProductList,
  },
  computed: {
    ...mapState(["userInfo", "productListData"]),
  },
  mounted() {
    const userInfoId = this.userInfo.id;

    if (this.productListData !== null) {
      this.myPostData = this.productListData.filter(
        (item) => userInfoId == item.userId
      );
    } else {
      this.myPostData = null;
    }
  },
};
</script>
