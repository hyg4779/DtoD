<template>
  <div class="signupform">
    <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-md form"
    >
      <div class="checkbox">
        <div>
          <q-checkbox v-model="stacks.javascript" label="JavaScript"/>
          <q-checkbox v-model="stacks.react" label="React.js"/>
          <q-checkbox v-model="stacks.vue" label="Vue.js"/>
          <q-checkbox v-model="stacks.node" label="Node.js"/>
        </div>
        <div>
          <q-checkbox v-model="stacks.c" label="C"/>
          <q-checkbox v-model="stacks.cpp" label="C++"/>
          <q-checkbox v-model="stacks.cs" label="C#"/>
          <q-checkbox v-model="stacks.typescript" label="TypeScript.js"/>
        </div>
        <div>
          <q-checkbox v-model="stacks.kotlin" label="Kotlin"/>
          <q-checkbox v-model="stacks.django" label="Django"/>
          <q-checkbox v-model="stacks.go" label="Go"/>
          <q-checkbox v-model="stacks.swift" label="Swift"/>
        </div>
        <div>
          <q-checkbox v-model="stacks.java" label="Java"/>
          <q-checkbox v-model="stacks.spring" label="Spring"/>
          <q-checkbox v-model="stacks.flutter" label="Flutter"/>
          <q-checkbox v-model="stacks.etc" label="Etc"/>
        </div>
      </div>

      <div class="signupbtn">
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
        javascript: false,
        c: false,
        kotlin: false,
        java: false,
        react: false,
        cpp: false,
        django: false,
        spring: false,
        vue: false,
        cs: false,
        go: false,
        flutter: false,
        node: false,
        typescript: false,
        swift: false,
        etc: false,
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
          if (stacks[property]){
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

<style scoped>
form {
  margin: 20px 0 0 20px;
}

form .checkbox {
  display: grid;
  grid-template-columns: auto auto auto auto;
  margin: 0;
}
form .checkbox div{
  margin-right: 11px;
}
.form .signupbtn {
  margin: 50px 0 0 0 !important;
  /* text-align: center; */
}
</style>