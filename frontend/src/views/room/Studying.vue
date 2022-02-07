<template>
  <div class="studying">
    <br>
    <div class="title">
      진행중인 스터디룸
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
import { dummy } from "../../../generated.js";
import Items from '../../components/room/Items.vue'

export default {
  name: 'Studying',
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

.studying .title {
  font-size: 1.58vw;
  margin: 2vh 0 0 10vw;
  font-weight: bold;
  color: #0D1350;
}

</style>