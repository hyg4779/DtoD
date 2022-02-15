<template>
  <div class="myboard">
    <div class="title">
      내가 작성한 글
    </div>
    <div class="myitems">
      <Items
        :items="this.items"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'
import Items from './Items.vue'

export default {
  name: 'myboard',
  components: {
    Items,
  },
  data () {
    return {
      userName: '',
      items: [],
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
        url: api.GET_STUDY_BOARD,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
      // console.log(res)
        const temp = []
        res.data.forEach((element)=>{
          temp.push(element)
        })
        // console.log(temp)
        for (let i = 0; i < temp.length; i++){
          if (temp[i].user.userName === this.userName) {
            this.items.push(temp[i])
          }
        }
        // console.log(this.items)
      }).catch((err)=>{
        console.error(err)
      })

    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>

.myboard .title {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
}

</style>
