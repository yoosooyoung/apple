<template>
  <div class="join">
    <Logo />
    <form id="loginForm">
      <dl class="form-group">
        <dt class="form-title">
          <label for="userId" class="label-title">아이디</label>
        </dt>

        <dd class="form-data">
          <div class="input-div">
            <input
              type="text"
              class="input-basic h50"
              name="user_id"
              placeholder="아이디를 입력해주세요"
              @input="getLoginId($event.target.value)"
              maxlength="12"
            />
          </div>
        </dd>

        <dt class="form-title">
          <label for="nickName" class="label-title">비밀번호</label>
        </dt>

        <dd class="form-data">
          <div class="input-div">
            <input
              type="password"
              name="user_pw"
              class="input-basic h50"
              @input="getLoginPw($event.target.value)"
              placeholder="비밀번호를 입력해주세요"
              maxlength="20"
              autocomplete
            />
          </div>
        </dd>
      </dl>
    </form>

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
    ...mapMutations(["getLoginId", "getLoginPw", "login"]),
  },
};
</script>
