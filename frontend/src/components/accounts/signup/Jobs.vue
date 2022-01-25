<template>
  <div class="signupform">
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <div class="q-gutter-md">
        <q-select v-model="jobs" :options="options" label="직무" />
      </div>

      <div class="signupbtn">
        <q-btn label="다음" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat />
      </div>
    </q-form>
  </div>
</template>

<script>
export default {
  name: 'Jobs',
  props:{
    user:{
      type: Object,
    }
  },
  data () {

    
    return {
      jobs: null,
      options: [
        '웹 프로그래머',
        '웹 퍼블리셔',
        '프론트엔드 엔지니어',
        '서버 개발자',
        '백엔드 엔지니어',
        '데이터 엔지니어',
        '데브옵스 엔지니어',
      ]}
  },
  methods:{
    onSubmit () {
      if (this.jobs.length > 0) {
        console.log(this.user)
        alert('Success')
        this.$emit('jobs-fin', this.jobs)

      }

    },

    onReset () {
      this.jobs = null
    },
  }
}

</script>

<style>
.signupform {
  margin: 50px 20px 0 20px !important;
}


.signupform .signupbtn {
  margin: 20px;
  text-align: center;
}
</style>