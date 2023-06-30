<template>
  <div class="input-group">
    <input
      v-if="$route.params.id != null"
      @input="$emit('getTitle', $event.target.value)"
      class=""
      type="text"
      placeholder="제목"
      :value="title"
      name="board_title"
      required
    />

    <div class="price-input">
      <input
        @input="$emit('getPrice', $event.target.value)"
        type="number"
        placeholder="가격"
        :value="price"
        name="board_price"
        required
      />
      <span v-if="price == null || price == ''" class="won color-gray">￦</span>
      <span v-else class="won">￦</span>
    </div>

    <textarea
      @input="$emit('getContent', $event.target.value)"
      name="board_content"
      cols="30"
      rows="10"
      :value="content"
      placeholder="게시글 내용을 작성해주세요(가품 및 판매 금지 물품은 게시가 제한될 수 있어요)"
      required
    ></textarea>
  </div>
</template>
<script>
export default {
  name: "InputGroup",
  props: {
    title: String,
    price: String,
    content: String,
  },
};
</script>
