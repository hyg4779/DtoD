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
import axios from 'axios'
// import { dummy } from "../../../generated.js";
import { api } from '../../../api.js'
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
    if (localStorage.getItem('jwt')) {
      // this.items = dummy
      const token = localStorage.getItem('jwt')
      axios({
        url: api.GET_STUDY_ROOM,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        // console.log(res)
        this.items = res.data
      }).catch(err=>{
        console.error(err)
      })
    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
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