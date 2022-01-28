<template>
  <div class="myboard">
    <div class="title">
      내가 작성한 글
    </div>
    <div>
      <Tiptap />
    </div>
  </div>
</template>

<script>
import Tiptap from '../editor/Tiptap.vue'

export default {
  components: {
    Tiptap,
  }
}
</script>

<style scoped>

.myboard .title {
  font-size: 30px;
  font-weight: bold;
  color: #0D1350;
  margin: 20px 0 0 20px;
}

</style>
