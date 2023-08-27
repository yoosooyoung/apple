<template>
  <div class="product-info">
    <h3 class="product-info-title">
      {{ productData.BOARD_TITLE }}
    </h3>
    <ul class="product-info-cate">
      <!-- BACK TO DO : 카테고리 데이터 필요 - 230827 JHJ-->
      <li class="cate">전자기기</li>
      <li>{{ productData.BOARD_DATE }}</li>
    </ul>

    <div class="product-content">
      <p>
        {{ productData.BOARD_CONTENT }}
      </p>
    </div>

    <!-- BACK TO DO : 채팅, 관심 , 조회 데이터 필요  - 230827 JHJ-->
    <ul class="product-info-popular">
      <li>채팅 11</li>
      <li>관심 10</li>
      <li>조회 50</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ProductInfo",
  props: {
    productData: Object,
  },
};
</script>
