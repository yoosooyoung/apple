<template>
  <div>
    <!-- BOARD_CONTENT
: 
"싸다"
BOARD_DATE
: 
"2023.07.06 20:43"
BOARD_PRICE
: 
"10000"
BOARD_SEQ
: 
"6"
BOARD_TITLE
: 
"귀여움"
PICTURE_URL
: 
"../uplaod/에어팟맥스2.jpg"
USER_ID
: 
"test" -->
    <!-- <img src="../upload/아이패드2.jpg" /> -->
    <!-- profileUrl: require("../../assets/images/person1.jpg") -->
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
            <!-- :src="require(item.PICTURE_URL)" -->
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
              <li>to. 백 : 위치추가되어야함</li>
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
