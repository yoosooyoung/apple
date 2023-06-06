<template>
  <nav class="global-tab-bar">
    <h1 class="visually-hidden">탭바</h1>
    <ul class="tab-bar-list">
      <li class="tab-bar-item">
        <button
          aria-label="홈"
          class="tab-bar-button"
          @click="$router.push('/')"
        >
          <i v-if="$route.path != `/`" class="xi-home-o"></i>
          <i v-else class="xi-home"></i>
          <span class="txt">홈</span>
        </button>
      </li>
      <li class="tab-bar-item">
        <button
          aria-label="채팅"
          class="tab-bar-button"
          @click="$router.push('/chat')"
        >
          <i v-if="$route.path != `/chat`" class="xi-forum-o"></i>
          <i v-else class="xi-forum"></i>
          <span class="txt">채팅</span>
        </button>
      </li>
      <li class="tab-bar-item">
        <button
          aria-label="나의사과"
          class="tab-bar-button"
          @click="$router.push('/mypage')"
        >
          <i v-if="$route.path != `/mypage`" class="xi-user-o"></i>
          <i v-else class="xi-user"></i>
          <span class="txt">나의사과</span>
        </button>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "GlobalTabBar",
};
</script>
