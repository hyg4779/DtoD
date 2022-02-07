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
import { dummy } from "../../../../generated.js";

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
    // console.log(this.item_pk)
    const temp = dummy
    for (let i=0; i < temp.length; i++) {
      if (temp[i].id === this.item_pk) {
        this.title = temp[i].title
        this.content1 = temp[i].content
        this.content2 = temp[i].content
        this.content3 = temp[i].content
      }
    }

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