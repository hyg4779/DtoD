<template>
  <div>
    <div class="comment row">
      <div class="col-2">
        <p><b>{{ getName }}</b></p>
      </div>

      <div class="col-8">
        <p class="text-left" style="color:white">{{ getComment }}</p>
      </div>

      <div class="col-2">
        <button v-if="getName == currentName" @click="deleteComment">댓글 삭제</button>
      </div>
    </div>
    <br>
  </div>
</template>

<script>
// import axios from 'axios'
// import jwt_decode from 'jwt-decode'

export default {
  data() {
    return {
      currentName: '',
    }
  },
  props: {
    comment: Object,
    item_pk: Number
  },
  computed: {
    getComment() {
      return this.comment.content
    },
    getName() {
      return this.comment.user.username
    }
  },
  methods: {
    deleteComment() {
    }
  },
  created() {
  }
}
</script>

<style>
  /* .comment {

  } */
</style>