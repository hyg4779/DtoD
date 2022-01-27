<template>
  <div class="itemdetail">
    <div class="cardmodal">
      <div>
        {{this.title}}
      </div>
      <br>
      <div>
        {{this.content}}
      </div>
      <br>
      <div>
        {{this.code}}
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
      content: '',
      code: '',
    }
  },
  created() {
    // console.log(this.item_pk)
    const temp = dummy
    for (let i=0; i < temp.length; i++) {
      if (temp[i].id === this.item_pk) {
        this.title = temp[i].title
        this.content = temp[i].content
        this.code = temp[i].code
      }
    }

  }
}
</script>

<style scoped>
.itemdetail{
  width: auto; 
  height:auto; 
  padding:10px !important;
  font-size: 20px;
}
</style>