<template>
  <div class="questionboard">
    <div class="title">
      질문 게시판
    </div>
    <div class="allitems">
      <QuestionItems
        :items="items"
      />
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import { dummy } from "../../../../generated.js";
import QuestionItems from './QuestionItems.vue'

export default {
  name: 'QuestionBoard',
  components: {
    QuestionItems,
  },
  data () {
    return {
      items: [],
    }
  },
  created() {
    this.items = dummy
  }
}
</script>

<style scoped>

.questionboard .title {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
}

</style>