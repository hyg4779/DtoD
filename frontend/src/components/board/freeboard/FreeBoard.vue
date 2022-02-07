<template>
  <div class="freeboard">
    <div class="title">
      자유 게시판
    </div>
    <div class="freeitems">
      <FreeItems
        :items="items"
      />
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import { dummy } from "../../../../generated.js";
import FreeItems from './FreeItems.vue'

export default {
  name: 'FreeBoard',
  components: {
    FreeItems,
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

.freeboard .title {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
}

</style>