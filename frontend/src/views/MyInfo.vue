<template>
  <div class="my-info">
    <div class="editable-avatar">
      <label for="editable-avatar">
        <i class="xi-camera" aria-hidden="true"></i>
        <img
          v-if="userInfo.image == null"
          src="../assets/img-user-default.png"
          alt=""
        />

        <img v-else :src="`${userInfo.image}`" alt="" />
      </label>
      <input
        type="file"
        id="editable-avatar"
        class="visually-hidden"
        @change="getUserImage($event.target)"
      />
    </div>

    <dl class="form-group">
      <dt class="form-title">
        <label for="editUserId" class="label-title">아이디</label>
      </dt>
      <dd class="form-data">
        <div class="input-div">
          <input
            id="editUserId"
            class="input-basic h50"
            type="text"
            :value="userInfo.id"
            readonly
          />
        </div>
      </dd>

      <dt class="form-title">
        <label for="editUserNickName" class="label-title">닉네임</label>
      </dt>
      <dd class="form-data">
        <div class="input-div">
          <input
            class="input-basic h50"
            id="editUserNickName"
            type="text"
            :value="editUserNickName"
            @input="getUserNickName($event.target.value)"
            maxlength="8"
          />
        </div>
      </dd>

      <dt class="form-title">
        <label for="editUserEmail" class="label-title">이메일</label>
      </dt>
      <dd class="form-data">
        <div class="input-div">
          <input
            class="input-basic h50"
            id="editUserEmail"
            type="text"
            :value="editUserEmail"
            @input="getUserEmail($event.target.value)"
          />
        </div>
      </dd>

      <dt class="form-title">
        <label for="editUserPwOri" class="label-title">기존 비밀번호</label>
      </dt>
      <dd class="form-data">
        <div class="input-div">
          <input
            class="input-basic h50"
            id="editUserPwOri"
            type="text"
            :value="editUserPwOri"
            @input="getUserPwOri($event.target.value)"
            maxlength="8"
          />
        </div>
      </dd>

      <dt class="form-title">
        <label for="editUserPwNew" class="label-title">새 비밀번호</label>
      </dt>
      <dd class="form-data">
        <div class="input-div">
          <input
            class="input-basic h50"
            id="editUserPwNew"
            type="text"
            :value="editUserPwNew"
            @input="getUserPwNew($event.target.value)"
            maxlength="8"
          />
        </div>
      </dd>

      <dt class="form-title">
        <label for="editUserPwNew" class="label-title">새 비밀번호 확인</label>
      </dt>
      <dd class="form-data">
        <div class="input-div">
          <input
            class="input-basic h50"
            id="editUserPwNew"
            type="text"
            :value="editUserPwNew2"
            @input="getUserPwNew2($event.target.value)"
            maxlength="8"
          />
        </div>
      </dd>
    </dl>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
export default {
  Name: "MyInfo",
  computed: {
    ...mapState([
      "userInfo",
      "editUserNickName",
      "editUserEmail",
      "editUserPwOri",
      "editUserPwNew",
      "editUserPwNew2",
    ]),
  },
  methods: {
    ...mapMutations([
      "getUserImage",
      "getUserNickName",
      "getUserEmail",
      "getUserPwOri",
      "getUserPwNew",
      "getUserPwNew2",
    ]),
  },
};
</script>
