<template>
  <q-card class="cardmodal">
    <q-card-section class="row items-center q-pb-none">
      <div class="text-h6">Close icon</div>
      <q-space />
      <q-btn icon="close" flat round dense v-close-popup />
    </q-card-section>
    <q-card-section>
      {{this.title}}
    </q-card-section>
    <q-card-section>
      {{this.content}}
    </q-card-section>
    <q-card-section>
      {{this.code}}
    </q-card-section>
  </q-card>
</template>

<script>
import { reactive, ref } from 'vue'
import { dummy } from "../../../../generated.js";
// import axios from 'axios'

export default {
  name: 'ItemDetail',
  props: {
    item_pk: Number,
  },
  // data() {
  //   return {
  //     title: '',
  //     content: '',
  //     code: '',
  //   }
  // },
  setup () {
    const state = reactive({
      title: '',
      content: '',
      code: '',
    })
    return {
      state,
      icon: ref(false),
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

<style>

</style>