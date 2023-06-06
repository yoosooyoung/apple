<template>
  <div class="product-info">
    <h3 class="product-info-title">
      {{ data.title }}
    </h3>
    <ul class="product-info-cate">
      <li class="cate">{{ data.category }}</li>
      <li>{{ data.datetime }}</li>
    </ul>

    <div class="product-content">
      <p>
        {{ data.content }}
      </p>
    </div>

    <ul class="product-info-popular">
      <li>채팅 {{ data.chatCnt }}</li>
      <li>관심 {{ data.likeCnt }}</li>
      <li>조회 {{ data.viewCnt }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ProductInfo",
  props: {
    data: Object,
  },
};
</script>
