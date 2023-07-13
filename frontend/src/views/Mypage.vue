<template>
  <div class="my-page">
    <UserInfo
      :userImage="userInfo.image"
      :userId="userInfo.id"
      :userNickName="userInfo.nickName"
      :userLocation="userInfo.location"
      :editButton="true"
    />

    <ul class="mypage-menu-list">
      <li>
        <RouterLink to="/mywish"><i class="xi-heart-o"></i>관심상품</RouterLink>
      </li>
      <li>
        <RouterLink to="/mypost">
          <i class="xi-paper-o"></i>내가쓴글</RouterLink
        >
      </li>
    </ul>

    <button type="button" class="btn-logout button-normal h38" @click="logout">
      로그아웃
    </button>
  </div>
</template>

<script>
import UserInfo from "@/components/UserInfo.vue";
import { RouterLink } from "vue-router";
import { mapState, mapMutations } from "vuex";

export default {
  name: "Mypage",
  data() {
    return {};
  },
  components: {
    UserInfo,
    RouterLink,
  },
  computed: {
    ...mapState(["userInfo"]),
  },
  methods: {
    ...mapMutations(["logout"]),
  },
};
</script>
