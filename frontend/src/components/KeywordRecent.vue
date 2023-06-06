<template>
  <div class="keyword-recent">
    <header>
      <h3>최근검색어</h3>
      <button
        type="button"
        aria-label="최근 검색어 모두 지우기"
        @click="$store.commit('deleteRecentKeyowrdAll')"
      >
        모두 지우기
      </button>
    </header>

    <ul v-if="$store.state.recentKeyword !== null" class="keyword-list">
      <li v-for="(item, idx) in $store.state.recentKeyword" :key="idx">
        <button
          @click="$store.commit('searchItem', $event.target.value)"
          type="button"
          class="keyword"
          :value="item"
        >
          {{ item }}
        </button>
        <button
          type="button"
          class="button-delete"
          @click="$store.commit('deleteRecentKeyowrd', Number(idx))"
        >
          <i class="xi-close"></i>
        </button>
      </li>
    </ul>

    <ul v-else class="keyword-list">
      <li class="empty">최근 검색어가 없습니다</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "KeywordRecent",
};
</script>
