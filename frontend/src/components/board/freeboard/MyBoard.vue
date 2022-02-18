<template>
  <div class="myboard">
    <body>
      <article v-if="this.freeitems.length !== 0">
        <p>내가 작성한 자유 게시글</p>
        <MyFreeItem
          :items="this.freeitems"
        />
      </article>
      <br>
      <article v-if="this.questionitems.length !== 0">
        <p>내가 작성한 질문 게시글</p>
        <MyQuestionItem
          :items="this.questionitems"
        />
      </article>
    </body>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'
import MyFreeItem from './myitems/MyFreeItem.vue'
import MyQuestionItem from './myitems/MyQuestionItem.vue'

export default {
  name: 'myboard',
  components: {
    MyFreeItem,
    MyQuestionItem,
  },
  data () {
    return {
      userName: '',
      freeitems: [],
      questionitems: [],
    }
  },
  created() {
    const token = localStorage.getItem('jwt')
    axios({
      url:  api.USER_INFO_GET,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.userName = res.data.userName

      axios({
        url: api.GET_FREE_BOARD,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
      console.log(res)
        for (let i=0; i < res.data.length; i ++) {
          // console.log(res.data[i].cboardType)
          if (res.data[i].cboardType === "자유") {
            if (res.data[i].user.userName ===this.userName) {
              this.freeitems.push(res.data[i])
            }
          }
          if (res.data[i].cboardType === '질문') {
            if (res.data[i].user.userName ===this.userName) {
              this.questionitems.push(res.data[i])
            }
          }
        }
      }).catch((err)=>{
        console.error(err)
      })

    }).catch((err)=>{
      console.error(err)
    })

    // axios({
    //   url: api.GET_FREE_BOARD,
    //   method: 'GET',
    //   headers: {
    //     Authorization: 'Bearer ' + token
    //   },
    // }).then((res)=>{
    //   // console.log(res)
    //   for (let i=0; i < res.data.length; i ++) {
    //     // console.log(res.data[i].cboardType)

    //   }
    //   // console.log(this.items)
    // }).catch((err)=>{
    //   console.error(err)
    // })
  }
}
</script>

<style scoped>
body {
  margin: 6vh auto;
}


body p {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
}

</style>