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
      images: [
        '../../../assets/color/001.png',
        '../../../assets/color/002.png',
        '../../../assets/color/003.png',
      ]
    }
  },
  created() {
    // this.items = dummy
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')
      axios({
        url: api.GET_STUDY_BOARD,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        const temp = []
        res.data.forEach((element)=>{
          temp.push(element)
        })
        this.items = temp
        // console.log(this.items)
      }).catch((err)=>{
        console.error(err)
      })
    } else {
      alert('로그인을 해주세요')
    }
  console.log(this.images)
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