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
              @click="$store.commit('idCheckOverlap')"
            >
              중복체크
            </button>
          </div>

          <!-- id 유효성검사 -->
          <p class="desc red" v-if="$store.state.idCheck === 'null'">
            아이디를 입력해주세요
          </p>
          <p class="desc red" v-if="$store.state.idCheck === 'rule'">
            영문, 숫자를 포함한 4자 이상 12자 이하여야 합니다.
          </p>
          <p class="desc blue" v-if="$store.state.idCheck === 'pass'">
            사용가능한 아이디입니다.
          </p>
          <p class="desc red" v-if="$store.state.idCheck === 'overlap'">
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
              required
            />
          </div>
          <!-- pw 유효성검사 -->
          <p class="desc red" v-if="$store.state.pwCheck === 'null'">
            비밀번호를 입력해주세요.
          </p>
          <p class="desc red" v-if="$store.state.pwCheck === 'rule'">
            영문, 숫자를 조합하여 6 ~ 20자 사이로 입력해주세요.
          </p>
          <p class="desc blue" v-if="$store.state.pwCheck === 'pass'">
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
              required
            />
          </div>
          <!-- pw 확인 체크 -->
          <p class="desc red" v-if="$store.state.pwCheck2 === 'null'">
            비밀번호 확인을 입력해주세요.
          </p>
          <p class="desc red" v-if="$store.state.pwCheck2 == 'rule'">
            비밀번호가 일치하지 않습니다.
          </p>
          <p class="desc blue" v-if="$store.state.pwCheck2 == 'pass'">
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
          <p class="desc red" v-if="$store.state.nameCheck === 'null'">
            닉네임을 입력해주세요.
          </p>
          <p class="desc red" v-if="$store.state.nameCheck == 'rule'">
            2 ~ 12자 사이의 문자로 입력해주세요.
          </p>
          <p class="desc blue" v-if="$store.state.nameCheck == 'pass'">
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
          <p class="desc red" v-if="$store.state.emailCheck === 'null'">
            이메일을 입력해주세요.
          </p>
          <p class="desc red" v-if="$store.state.emailCheck == 'rule'">
            이메일 형식을 확인해주세요.
          </p>
          <p class="desc blue" v-if="$store.state.emailCheck == 'pass'">
            사용가능한 이메일입니다.
          </p>
        </dd>

        <dt class="form-title">
          <label class="required" for="nickName">주소</label>
        </dt>

        <!-- 주소 체크 : 진행중 -->
        <!-- <dd class="form-data">
          <div class="input-div">
            <input
              type="text"
              class="input-basic h50"
              placeholder="우편번호"
              v-model="postcode"
              @input="getJoinAddress(postcode)"
              readonly
              required
            />
            <button
              type="button"
              class="button-secondary h50"
              @click="execDaumPostcode()"
            >
              주소찾기
            </button>
          </div>
          <div class="input-div" id="address">
            <input
              type="text"
              class="input-basic h50"
              placeholder="주소"
              :value="address"
              readonly
              required
            />
          </div>
          <div class="input-div" id="detailAddress">
            <input
              type="text"
              class="input-basic h50"
              placeholder="상세주소"
              @input="getJoinAddress2($event.target.value)"
              required
            />
          </div>

          <p class="desc red" v-if="$store.state.addressCheck2 === 'null'">
            상세주소을 입력해주세요.
          </p>
        </dd> -->

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
import { mapActions, mapMutations, mapState } from "vuex";
import Logo from "./Logo.vue";

export default {
  name: "join",
  components: {
    Logo,
  },
  data() {
    return {
      postcode: "",
      address: "",
      extraAddress: "",
    };
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
      "getJoinAddress2",
      "getJoinProfile",
      //"join",
      "checkId",
    ]),
    ...mapActions(["join"]),

    // 카카오 주소찾기 api
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.postcode = data.zonecode;
        },
      }).open();
    },
  },
};
</script>
