<template>
  <div class="error-view">
    <h2>ERROR</h2>
    <p>
      존재하지 않는 주소를 입력하셨거나,<br />
      요청하신 페이지의 주소가 변경, 삭제되어 찾을 수 없습니다.
    </p>
    <button type="button" class="button-primary h50" @click="$router.push('/')">
      메인으로 가기
    </button>
  </div>
</template>

<script>
export default {
  components: {},
};
</script>
