<template>
  <v-container>
    <v-layout row>
      <v-flex class="text-center font-weight-black">
        <h1>Upload a photo</h1>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex md6 offset-sm3 class="text-center">
        <v-text-field
        solo
        v-model="caption"
        label="Caption goes here">
        </v-text-field>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex class="text-center">
        <v-btn color="pink" @click="create">upload</v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
// import axios from 'axios'
// import { api } from '../../../api.js'

export default {
  name: 'MyInfo',
  data() {
    return{
      caption : ''
    }
  },
  methods: {
    create () {
      console.log(this.caption)
    }
  },

}
</script>

<style>

</style>