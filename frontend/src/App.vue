<template>
  <div>
    <div class="layouts" v-if="userInfo != null">
      <GlobalHeader />

      <GlobalTabBar
        v-if="
          $route.path == '/' ||
          $route.path == '/mypage' ||
          $route.path == '/chat'
        "
      />

      <FloatButton v-if="$route.path == '/'" />

      <RouterView />
    </div>

    <Join v-else />

    <button @click="test">테스트버튼</button>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import GlobalHeader from "./components/GlobalHeader.vue";
import GlobalTabBar from "./components/GlobalTabBar.vue";
import FloatButton from "./components/FloatButton.vue";
import Join from "./components/Join.vue";

export default {
  name: "App",
  data() {
    return {
      isMyPost: false,
      myPostData: null,
    };
  },
  components: {
    GlobalHeader,
    GlobalTabBar,
    FloatButton,
    Join,
  },
  computed: {
    ...mapState(["userInfo", "productListData"]),
  },
  methods:{
     test() {
      axios
        .get("/api/join")
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  }
}
</script>

