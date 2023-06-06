<template>
  <div>
    <header class="global-header search-header">
      <h1 class="visually-hidden">애플마켓</h1>

      <h2 class="visually-hidden">검색하기</h2>

      <ul class="left-menu">
        <li>
          <button
            @click="$router.go(-1)"
            arial-lable="뒤로가기"
            class="button-back"
          >
            <i class="xi-angle-left"></i>
          </button>
        </li>
      </ul>
      <ul class="right-menu">
        <li>
          <div class="deletable-input">
            <input
              type="search"
              class="input-basic h40"
              aria-label="검색어 입력"
              placeholder="검색어를 입력해주세요"
              v-on:keyup.enter="
                $store.commit('searchItem', $event.target.value)
              "
              @input="$store.commit('searchEditing', $event.target.value)"
              :value="$store.state.searchKeyword"
            />
            <button
              class="button-delete"
              aria-label="전체 지우기"
              @click="$store.commit('clearInput')"
            >
              <i class="xi-close"></i>
            </button>
          </div>
        </li>
      </ul>
    </header>

    <!-- case 1. 검색버튼 클릭, 인풋 수정시 나타나는 컴포넌트 -->
    <KeywordRecent v-if="$store.state.searchResultMode === false" />

    <!-- case 2. 완료버튼 클릭시 나타나는 상품리스트 컴포넌트 -->
    <SearchResult v-else />
  </div>
</template>

<script>
import KeywordRecent from "../components/KeywordRecent.vue";
import SearchResult from "../components/SearchResult.vue";

export default {
  components: {
    KeywordRecent,
    SearchResult,
  },
};
</script>
