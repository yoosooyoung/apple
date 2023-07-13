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

    /* FRONT TO DO : 현재 USER_ID가 닉네임으로 들어옴. ID로 들어오게 수정된 뒤, 정상 작동 여부 확인하기 */
    if (this.productListData !== null) {
      this.myPostData = this.productListData.filter(
        (item) => userInfoId == item.USER_ID
      );
    } else {
      this.myPostData = null;
    }
  },
};
</script>
