<template>
  <div>
    <!-- 1-1. /error일때 -->
    <div v-if="$route.path == '/error'">
      <ErrorView />
    </div>

    <!-- 1-2. /error가 아닐때 -->
    <div v-else>
      <!-- 2-1. 로그인 시, -->
      <div class="layouts" v-if="userInfo !== null">
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
      <!-- 2-1. 비로그인 시, -->
      <div v-else>
        <Join v-if="$route.path == '/join'" />

        <Login v-else />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import GlobalHeader from "./components/GlobalHeader.vue";
import GlobalTabBar from "./components/GlobalTabBar.vue";
import FloatButton from "./components/FloatButton.vue";
import Join from "./components/Join.vue";
import Login from "./components/Login.vue";
import ErrorView from "./views/ErrorView.vue";

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
    Login,
    ErrorView,
  },
  computed: {
    ...mapState(["userInfo", "productListData"]),
  },
  methods: {
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
  },
  mounted() {
    this.$store.dispatch("getProductList");
  },
};
</script>
