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

    <div class="editable-inputs">
      <label for="nickName" class="label-title">닉네임</label>
      <input
        class="input-basic h50"
        id="nickName"
        type="text"
        :value="userInfo.nickName"
        @input="getUserNickName($event.target.value)"
        maxlength="8"
      />
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
export default {
  Name: "MyInfo",
  computed: {
    ...mapState(["userInfo"]),
  },
  methods: {
    ...mapMutations(["getUserImage", "getUserNickName"]),
  },
};
</script>
