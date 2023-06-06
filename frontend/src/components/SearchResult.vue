<template>
  <div class="search-result">
    <ProductList :productListData="$store.state.searchProductListData" />
  </div>
</template>

<script>
import ProductList from "./ProductList.vue";

export default {
  name: "SearchResult",
  components: {
    ProductList,
  },
};
</script>
