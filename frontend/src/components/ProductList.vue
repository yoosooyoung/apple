<template>
  <div>
    <ul class="product-list">
      <li class="product-empty" v-if="productListData === null">
        등록된 상품이 없습니다.
      </li>

      <li
        class="product-item"
        v-else
        v-for="(item, idx) in productListData"
        :key="idx"
      >
        <RouterLink :to="`/view/${item.id}`" class="product-item-links">
          <div class="product-item-img">
            <img :src="`${item.productMainImage}`" :alt="`${item.title}`" />
          </div>
          <div class="product-item-info">
            <p class="product-item-info-title">
              {{ item.title }}
            </p>
            <ul>
              <li>{{ item.userLocation }}</li>
              <li>{{ item.datetime }}</li>
            </ul>
            <strong>{{ item.price }}</strong>
          </div>
        </RouterLink>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ProductList",

  props: {
    productListData: Array,
  },
};
</script>
