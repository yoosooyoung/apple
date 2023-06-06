<template>
  <div class="user-info-group">
    <div class="avatar">
      <img
        v-if="userImage == null"
        src="../assets/img-user-default.png"
        alt=""
      />
      <img v-else :src="userImage" :alt="userNickName" />
    </div>
    <div class="user-info">
      <strong class="name">{{ userNickName }}</strong>
      <div class="address">{{ userLocation }}</div>
    </div>

    <button
      type="button"
      class="button-primary h38"
      v-if="editButton"
      @click="$router.push('/myinfo')"
    >
      정보수정
    </button>
  </div>
</template>
<script>
export default {
  name: "UserInfo",
  props: {
    //data: Object,
    userImage: String,
    userId: String,
    userNickName: String,
    userLocation: String,
    editButton: {
      type: Boolean,
      default: false,
    },
  },
};
</script>
