<template>
  <div class="join">
    <Logo />

    <form id="joinForm">
      <dl class="form-group">
        <dt class="form-title">
          <label class="required" for="userId">아이디</label>
        </dt>
        <dd class="form-data">
          <div class="input-div">
            <input
              type="text"
              name="user_id"
              class="input-basic h50"
              placeholder="사용하실 아이디를 입력해주세요"
              @input="getJoinId($event.target.value)"
              maxlength="12"
              required
            />

            <button
              type="button"
              class="button-secondary h50"
              @click="idCheckOverlap()"
            >
              중복체크
            </button>
          </div>

          <!-- id 유효성검사 -->
          <p class="desc red" v-if="idCheck === 'null'">
            아이디를 입력해주세요
          </p>
          <p class="desc red" v-if="idCheck === 'rule'">
            영문, 숫자를 포함한 4자 이상 12자 이하여야 합니다.
          </p>
          <p class="desc blue" v-if="idCheck === 'pass'">
            사용가능한 아이디입니다.
          </p>
          <p class="desc red" v-if="idCheck === 'overlap'">
            중복된 아이디입니다.
          </p>
        </dd>

        <dt class="form-title">
          <label class="required" for="nickName">비밀번호</label>
        </dt>
        <dd class="form-data">
          <div class="input-div">
            <input
              type="password"
              name="user_pw"
              class="input-basic h50"
              @input="getJoinPassword($event.target.value)"
              placeholder="사용하실 비밀번호를 입력해주세요"
              maxlength="20"
              autocomplete="off"
              required
            />
          </div>
          <!-- pw 유효성검사 -->
          <p class="desc red" v-if="pwCheck === 'null'">
            비밀번호를 입력해주세요.
          </p>
          <p class="desc red" v-if="pwCheck === 'rule'">
            영문, 숫자, 특수문자를 조합하여 6 ~ 20자 사이로 입력해주세요.
          </p>
          <p class="desc blue" v-if="pwCheck === 'pass'">
            사용가능한 비밀번호입니다.
          </p>
        </dd>

        <dt class="form-title">
          <label class="required" for="nickName">비밀번호 확인</label>
        </dt>
        <dd class="form-data">
          <div class="input-div">
            <input
              type="password"
              class="input-basic h50"
              @input="getJoinPassword2($event.target.value)"
              placeholder="사용하실 비밀번호를 입력해주세요"
              maxlength="20"
              autocomplete="off"
              required
            />
          </div>
          <!-- pw 확인 체크 -->
          <p class="desc red" v-if="pwCheck2 === 'null'">
            비밀번호 확인을 입력해주세요.
          </p>
          <p class="desc red" v-if="pwCheck2 == 'rule'">
            비밀번호가 일치하지 않습니다.
          </p>
          <p class="desc blue" v-if="pwCheck2 == 'pass'">
            비밀번호가 일치합니다.
          </p>
        </dd>

        <dt class="form-title">
          <label class="required" for="nickName">닉네임</label>
        </dt>
        <dd class="form-data">
          <div class="input-div">
            <input
              type="text"
              name="nick_name"
              class="input-basic h50"
              @input="getJoinName($event.target.value)"
              placeholder="사용하실 닉네임을 입력해주세요"
              maxlength="12"
              required
            />
          </div>
          <!-- 닉네임 체크 -->
          <p class="desc red" v-if="nameCheck === 'null'">
            닉네임을 입력해주세요.
          </p>
          <p class="desc red" v-if="nameCheck == 'rule'">
            2 ~ 12자 사이의 문자로 입력해주세요.
          </p>
          <p class="desc blue" v-if="nameCheck == 'pass'">
            사용가능한 닉네임입니다.
          </p>
        </dd>

        <dt class="form-title">
          <label class="required" for="nickName">이메일</label>
        </dt>
        <dd class="form-data">
          <div class="input-div">
            <input
              type="email"
              name="user_email"
              class="input-basic h50"
              @input="getJoinEmail($event.target.value)"
              placeholder="사용하실 이메일을 입력해주세요"
              required
            />
          </div>
          <!-- 이메일 체크 -->
          <p class="desc red" v-if="emailCheck === 'null'">
            이메일을 입력해주세요.
          </p>
          <p class="desc red" v-if="emailCheck == 'rule'">
            이메일 형식을 확인해주세요.
          </p>
          <p class="desc blue" v-if="emailCheck == 'pass'">
            사용가능한 이메일입니다.
          </p>
        </dd>

        <dt class="form-title">프로필 (선택)</dt>
        <dd class="form-data">
          <div class="profile-div">
            <label for="profileImg" class="button-secondary h50"
              >사진첨부</label
            >
            <input
              type="file"
              id="profileImg"
              name="user_profile"
              @change="getJoinProfile($event.target)"
            />
            <span v-if="joinProfileName !== ''" class="file-name">{{
              joinProfileName
            }}</span>
          </div>
        </dd>
      </dl>

      <button type="button" class="button-primary h50" @click="join()">
        완료
      </button>
    </form>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import Logo from "./Logo.vue";

export default {
  name: "join",
  components: {
    Logo,
  },

  computed: {
    ...mapState([
      "userInfo",
      "joinProfileName",
      "idCheck",
      "pwCheck",
      "pwCheck2",
      "nameCheck",
      "emailCheck",
    ]),
  },
  methods: {
    ...mapMutations([
      "getJoinId",
      "getJoinPassword",
      "getJoinPassword2",
      "getJoinName",
      "getJoinEmail",
      "getJoinAddress",
      "getJoinAddress2",
      "getJoinProfile",
      "clearJoinValidation",
      //"join",
      "checkId",
      "idCheckOverlap",
    ]),
    ...mapActions(["join"]),
  },
  mounted() {
    this.clearJoinValidation();
  },
};
</script>
