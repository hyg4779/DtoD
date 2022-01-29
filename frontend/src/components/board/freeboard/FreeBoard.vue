<template>
  <div class="allboard">
    <div class="title">
      전체 글
    </div>
    <div class="allitems">
      <Items
        :items="items"
      />
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import { dummy } from "../../../../generated.js";
import Items from './Items.vue'

export default {
  name: 'AllBoard',
  components: {
    Items,
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

.allboard .title {
  font-size: 30px;
  margin: 20px 0 0 20px;
  font-weight: bold;
  color: #0D1350;
}

</style>