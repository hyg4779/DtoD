<template>
  <div class="signupform">
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md"
    >
      <div class="row">
        <q-checkbox class="checkbox" v-model="stacks.val01" label="JavaScript"/>
        <q-checkbox class="checkbox" v-model="stacks.val02" label="C"/>
        <q-checkbox class="checkbox" v-model="stacks.val03" label="Kotlin"/>
        <q-checkbox class="checkbox" v-model="stacks.val04" label="Java"/>
      </div>
      <div class="row">
        <q-checkbox class="checkbox" v-model="stacks.val05" label="React.js"/>
        <q-checkbox class="checkbox" v-model="stacks.val06" label="C++"/>
        <q-checkbox class="checkbox" v-model="stacks.val07" label="Django"/>
        <q-checkbox class="checkbox" v-model="stacks.val08" label="Spring"/>
      </div>
      <div class="row">
        <q-checkbox class="checkbox" v-model="stacks.val09" label="Vue.js"/>
        <q-checkbox class="checkbox" v-model="stacks.val10" label="C#"/>
        <q-checkbox class="checkbox" v-model="stacks.val11" label="Go"/>
        <q-checkbox class="checkbox" v-model="stacks.val12" label="Flutter"/>
      </div>
      <div class="row">
        <q-checkbox class="checkbox" v-model="stacks.val13" label="Node.js"/>
        <q-checkbox class="checkbox" v-model="stacks.val14" label="Typescript.js"/>
        <q-checkbox class="checkbox" v-model="stacks.val15" label="Swift"/>
        <q-checkbox class="checkbox" v-model="stacks.val16" label="Etc"/>
      </div>
      <div class="row">
        <q-btn label="다음" type="submit" color="primary"/>
        <q-btn label="Reset" type="reset" color="primary" flat />
      </div>
    </q-form>
  </div>
</template>

<script>
import { ref,reactive } from 'vue'

export default {
  emits:['stacks-fin'],

  setup (props, {emit}) {
    const stacks = reactive({
      val01: false,
      val02: false,
      val03: false,
      val04: false,
      val05: false,
      val06: false,
      val07: false,
      val08: false,
      val09: false,
      val10: false,
      val11: false,
      val12: false,
      val13: false,
      val14: false,
      val15: false,
      val16: false,
      })

    return {
      stacks,
      bar: ref(false),
      bar2: ref(false),
      toolbar: ref(false),

      // 체크한 기술스텍만 배열에 담아 signup으로 보내기
      onSubmit () {
        let result = []

        for (let property in stacks){
          if (stacks[property] === true){
            result.push(property)
          }
        }
          // console.log(result)
          alert('Success')
          emit('stacks-fin', result)
  
        },

      // 체크한 기술스텍 초기화
      onReset (){
        for (let property in stacks){
          if (stacks[property]){
            stacks[property] = false
          }
        }
      },

      
    }
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