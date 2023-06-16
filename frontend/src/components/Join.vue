<template>
  <div class="join">
    <Logo />

    <form action="">
      <label for="userId" class="label-title">아이디</label>
      <input
        type="text"
        id="userId"
        class="input-basic h50"
        placeholder="사용하실 아이디를 입력해주세요"
        @input="getUserId($event.target.value)"
        maxlength="12"
      />

      <label for="nickName" class="label-title">닉네임</label>
      <input
        type="text"
        id="nickName"
        class="input-basic h50"
        @input="getUserName($event.target.value)"
        placeholder="사용하실 닉네임을 입력해주세요"
        maxlength="8"
      />

      <label for="nickName" class="label-title">이메일</label>
      <input
        type="email"
        id="email"
        class="input-basic h50"
        @input="getUserEmail($event.target.value)"
        placeholder="사용하실 이메일을 입력해주세요"
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

      <button type="button" class="button-primary h50" @click="join">
        완료
      </button>
    </form>
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
    ...mapMutations([
      "getUserId",
      "getUserName",
      "getUserEmail",
      "getUserPassword",
      "join",
    ]),
  },
};
</script>
