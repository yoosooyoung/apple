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
            <!-- FRONT TO DO :: 아이디 중복체크 분리
            <button
              type="button"
              class="button-secondary h50"
              @click="checkId()"
            >
              중복체크
            </button>
          --></div>

          <!-- FRONT TO DO :: 유효성검사 
          <p class="desc red">아이디를 입력해주세요</p>
          <p class="desc red">
            영문, 숫자를 포함한 4자 이상 20자 이하여야 합니다.
          </p>
          <p class="desc red">중복된 아이디입니다.</p>
          <p class="desc blue">사용가능한 아이디입니다.</p> -->
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
              required
            />
          </div>
          <!-- FRONT TO DO :: 유효성검사 
          <p class="desc red">
            영문, 숫자를 조합하여 6 ~ 20자 사이로 입력해주세요
          </p>
        --></dd>

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
              required
            />
          </div>
          <!-- FRONT TO DO :: 유효성검사 
          <p class="desc red">비밀번호가 일치하지 않습니다.</p>
        --></dd>

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
              maxlength="8"
              required
            />
          </div>
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
          <!-- FRONT TO DO :: 유효성검사 
          <p class="desc red">이메일 형식을 확인해주세요.</p>
          <p class="desc red">이메일을 입력해주세요.</p>
           -->
        </dd>

        <dt class="form-title">
          <label class="required" for="nickName">주소</label>
        </dt>
        <dd class="form-data">
          <div class="input-div">
            <input
              type="text"
              name="user_address"
              class="input-basic h50"
              @input="getJoinAddress($event.target.value)"
              placeholder="주소찾기"
              required
            />

            <!-- FRONT TO DO :: 카카오 api넣기 
            <input
              type="text"
              class="input-basic h50"
              @input="getJoinAddress($event.target.value)"
              placeholder="주소찾기"
              required
            />
            <button type="button" class="button-secondary h50">주소찾기</button>
            -->
          </div>
        </dd>

        <dt class="form-title">프로필 (선택)</dt>
        <dd class="form-data">
          <div class="profile-div">
            <label for="profileImg" class="button-secondary h50"
              >사진첨부</label
            >
            <input
              type="file"
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
import { mapMutations, mapState } from "vuex";
import Logo from "./Logo.vue";

export default {
  name: "join",
  components: {
    Logo,
  },
  computed: {
    ...mapState(["userInfo", "joinProfileName"]),
  },
  methods: {
    ...mapMutations([
      "getJoinId",
      "getJoinPassword",
      "getJoinPassword2",
      "getJoinName",
      "getJoinEmail",
      "getJoinAddress",
      "getJoinProfile",
      "join",
      "checkId",
    ]),
  },
};
</script>
