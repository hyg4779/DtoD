<template>
  <div class="allboard">
    <div class="title">
      전체 글
    </div>
    <div class="allitems">
      <Items
        :items="this.items"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'
// import { dummy } from "../../../../generated.js";
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
    const token = localStorage.getItem('jwt')
    axios({
      url: api.GET_STUDY_BOARD,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      // const temp = []
      // res.data.forEach((element)=>{
        // console.log(element)
      //   temp.push(element)
      // })
      // console.log(temp)
      for(let i=0; i < res.data.length; i++) {
        this.items.push(res.data[i])
      }
      // console.log(this.items)
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>

.allboard .title {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
}

</style>