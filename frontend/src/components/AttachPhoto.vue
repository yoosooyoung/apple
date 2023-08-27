<template>
  <div class="attach-photo">
    <ul class="attach-photo-list">
      <li>
        <label
          for="buttonAttach"
          class="button-attach"
          aria-label="사진 첨부하기"
        >
          <i class="xi-camera"></i>
          <span v-if="isAttached === false">사진 첨부하기</span>
          <span v-else>{{ imgUrlArrayLength }} / 10</span>
        </label>
        <input
          type="file"
          multiple
          id="buttonAttach"
          @change="$emit('getImageUrl', $event.target)"
          required="true"
        />
      </li>
      <li
        v-if="isAttached || imgUrlArray != []"
        v-for="(item, idx) in imgUrlArray"
        :key="idx"
      >
        <button
          type="button"
          class="button-delete"
          @click="$emit('deletePhoto', idx)"
        >
          <i class="xi-close"></i>
        </button>
        <!-- <img :src="require(`../upload/${item}`)" alt="" /> -->
        <img :src="item" alt="" />
        <span class="badge" v-if="idx == 0">대표</span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "AttachPhoto",
  data() {
    return {};
  },
  props: {
    imgUrlArray: Array,
    imgUrlArrayLength: Number,
    isAttached: Boolean,
  },
};
</script>
