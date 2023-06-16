<template>
  <div class="join">
    <Logo />

    <label for="userId" class="label-title">아이디</label>
    <input
      type="text"
      id="userId"
      class="input-basic h50"
      placeholder="사용하실 아이디를 입력해주세요"
      @input="getUserId($event.target.value)"
      maxlength="12"
    />

    <label for="nickName" class="label-title">비밀번호</label>
    <input
      type="text"
      id="password"
      class="input-basic h50"
      @input="getUserPassword($event.target.value)"
      placeholder="사용하실 비밀번호를 입력해주세요"
      maxlength="20"
    />

    <div class="induce-join">
      <p>계정이 없으신가요? 그러면 가입하세요</p>
      <routerLink to="/join">회원가입</routerLink>
    </div>

    <button type="button" class="button-primary h50" @click="login">
      완료
    </button>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import Logo from "./Logo.vue";

export default {
  name: "join",
  components: {
    Logo,
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  methods: {
    ...mapMutations(["getUserId", "getUserName", "getUserPassword", "login"]),
  },
};
</script>
