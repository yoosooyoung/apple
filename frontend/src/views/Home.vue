<template>
  <ProductList :productListData="productListData" />
</template>

<script>
import { mapState } from "vuex";
import ProductList from "../components/ProductList";

export default {
  name: "Home",
  components: {
    ProductList,
  },
  computed: {
    ...mapState(["productListData"]),
  },
};
</script>
