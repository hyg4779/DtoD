<template>
  <div class="questionboard">
    <div class="title">
      질문 게시판
    </div>
    <div class="allitems">
      <QuestionItems
        :items="this.items"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'
// import { dummy } from "../../../../generated.js";
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
// this.items = dummy
    const token = localStorage.getItem('jwt')
    axios({
      url: api.GET_FREE_BOARD,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      for (let i=0; i < res.data.length; i ++) {
        // console.log(res.data[i].cboardType)
        if (res.data[i].cboardType === '질문') {
          this.items.push(res.data[i])
        }
      }
      // console.log(this.items)
    }).catch((err)=>{
      console.error(err)
    })
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