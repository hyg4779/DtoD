<template>
  <div class="mystudy">
    <br>
    <div class="madebyme" v-if="this.madeitems">
      <div class="title">
        내가 만든 스터디
      </div>
      <div class="allitems">
        <MyItems
        :items="this.madeitems"
        />
      </div>
    </div>
    <br>
    <br>
    <div class="joinedstudy" v-if="this.joineditems">
      <div class="title">
        내가 참여하는 스터디
      </div>
      <div class="allitems">
        <Items
        :items="this.joineditems"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
// import { dummy } from "../../../generated.js";
import { api } from '../../../api.js'

import MyItems from '../../components/profile/madebyme/MyItems.vue'
import Items from '../../components/profile/joined/Items.vue'

export default {
  name: 'MyStudy',
  components: {
    MyItems,
    Items
  },
  data () {
    return {
      username:'',

      madeitems: [],
      joineditems: [],
    }
  },
  created() {
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')

      axios ({
        method: 'get',
        url: api.USER_INFO_GET,
        headers: { 
          Authorization: 'Bearer ' + token
        }
      }).then(res=>{
        // console.log(res)
        this.username = res.data.userName
      }).catch(error => {
        console.log(error)
      })

      axios({
        url: api.GET_STUDY_ROOM,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        console.log(res)
        for (let i=0; i < res.data.length; i++){
          if( this.username === res.data[i].user.userName) {
            this.madeitems.push(res.data[i])
          }
        }
      }).catch(err=>{
        console.log(err)
      })

      axios({
        url: api.GET_MY_ROOM,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        console.log(res)
        this.joineditems = res.data
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
.mystudy {
  display: flex;
  flex-direction: column;
}

.mystudy .title {
  font-size: 1.58vw;
  margin: 1vh 0 0 4vw;
  font-weight: bold;
  color: #0D1350;
}
</style>