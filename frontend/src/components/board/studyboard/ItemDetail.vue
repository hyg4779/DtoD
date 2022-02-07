<template>
  <div class="itemdetail">
    <div class="cardmodal">
      <div>
        {{this.title}}
      </div>
      <br>
      <div>
        {{this.content1}}
      </div>
      <br>
      <div>
        {{this.content2}}
      </div>
      <br>
      <div>
        {{this.content3}}
      </div>
    </div>
  </div>
</template>

<script>
import { api } from '../../../../api.js'
import axios from 'axios'

export default {
  name: 'ItemDetail',
  props: {
    item_pk: Number,
  },
  data() {
    return {
      title: '',
      tech:'',
      content1: '',
      content2: '',
      content3: '',
      userImg: '',

      myComments: '',
      comments: [],
    }
  },
  methods:{
    commentSubmit() {
      
    },
    onParentDeleteComment() {

    },
    deleteArticle() {

    },
    updateArticle() {
      
    }
  },
  created() {
    const token = localStorage.getItem('jwt')
    axios({
      url:  api.GET_STUDY_BOARD_DETAIL + `${this.item_pk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      this.title = res.data.sboardTitle
      this.tech = res.data.sboardTechstack
      this.content1 = res.data.sboardContent1
      this.content2 = res.data.sboardContent2
      this.content3 = res.data.sboardContent3
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
.itemdetail{
  width: auto; 
  height:auto; 
  border-radius: 20px !important; 
  padding:10px !important;
  font-size: 20px;
}
</style>