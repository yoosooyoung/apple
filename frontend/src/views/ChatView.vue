<template>
  <div>
    <ChatMessageList />
    <ChatMessageBox />
  </div>
</template>

<script>
import ChatMessageList from "../components/ChatMessageList.vue";
import ChatMessageBox from "../components/ChatMessageBox.vue";

export default {
  components: {
    ChatMessageList,
    ChatMessageBox,
  },
};
</script>
