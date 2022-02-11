<template>
  <div class="editmystudy">
   스터디룸 정보수정
  </div>
</template>

<script>
export default {
  name: 'EditMyStudy',
  components: {

  },
  data () {
    return {

    }
  },
  created() {
    if (localStorage.getItem('jwt')) {
      // const token = localStorage.getItem('jwt')
      // console.log(token)
    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
  }
}
</script>

<style>

</style>