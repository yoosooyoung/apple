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
        <RouterLink :to="`/view/${item.BOARD_SEQ}`" class="product-item-links">
          <div class="product-item-img">
            <img
              :src="require(`@/upload/${item.PICTURE_URL}`)"
              :alt="`${item.BOARD_TITLE}`"
            />
          </div>
          <div class="product-item-info">
            <p class="product-item-info-title">
              {{ item.BOARD_TITLE }}
            </p>
            <ul>
              <!-- BACK TO DO : 위치추가 - 230827 JHJ-->
              <li>중화동</li>
              <li>{{ item.BOARD_DATE }}</li>
            </ul>
            <strong>{{ item.BOARD_PRICE }}</strong>
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
