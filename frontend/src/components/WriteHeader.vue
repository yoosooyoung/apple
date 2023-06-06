<template>
  <header class="global-header">
    <h1 class="visually-hidden">애플마켓</h1>
    <ul class="left-menu">
      <li>
        <button
          @click="$router.go(-1)"
          arial-lable="뒤로가기"
          class="button-back"
        >
          <i class="xi-angle-left"></i>
        </button>
      </li>
      <li>
        <h2 class="title">중고거래 글쓰기</h2>
      </li>
    </ul>

    <ul class="right-menu">
      <li>
        <button
          v-if="$route.params.id"
          @click="$emit('saveCustomData')"
          class="button-submit"
          aria-label="수정 완료"
        >
          수정완료
        </button>

        <button
          v-else
          @click="$emit('saveData')"
          class="button-submit"
          aria-label="글쓰기 작성완료"
        >
          완료
        </button>
      </li>
    </ul>
  </header>
</template>

<script>
export default {
  name: "WriteHeader",
};
</script>
