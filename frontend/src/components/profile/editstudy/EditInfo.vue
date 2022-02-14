<template>
  <div class="editinfo">
    <div class="title">
      스터디룸 정보 수정
    </div>
    <form @submit="onSubmit">
      <div class="form-group">
        <label for="title">제목</label>
        <br>
        <textarea class="form-control" type="text" id="title" v-model="title" placeholder=" 제목을 입력하세요"></textarea>
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <br>
        <input class="form-control" type="password" id="password" v-model="password" placeholder=" 비밀번호를 입력하세요" />
      </div>
      <div class="people form-group">
        <div class="peopletitle">인원</div>
        <div class="peoplecount">
          <input type="button" class="minusbtn" @click='count("minus")' value="-">
          <div id='result'>0</div>
          <input type="button" class="plusbtn" @click='count("plus")' value='+'>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'EditInfo'
}
</script>

<style scoped>

.editinfo .title {
  font-size: 1.58vw;
  margin: 2.5vh 0 0 1vw;
  font-weight: bold;
  color: #0D1350;
}

form {
  margin: 2vh 0 0 1vw;
}

form div label {
  font-weight: bold;
  font-size: 1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

form div textarea {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  line-height: 2.2;
  width: 30vw;
  font-family: 'Epilogue', sans-serif;
}

.people {
  margin: 0 0 3vh 0;
}
.people .peopletitle {
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

.people .peoplecount {
  display: flex;
}

.people #result {
  font-size: 1vw;
}

.people .peoplecount .minusbtn {
  cursor: pointer;
  font-family: 'Epilogue', sans-serif;
  font-size: 1vw;
  font-weight: bold;
  color: #24274A;
  padding: 0 0.6vw 0 0.6vw;
  margin: 0 1vw 0 0;
  border: 1px solid #24274A;
  background-color: white;
  border-radius: 1.1rem;
}
.people .peoplecount .plusbtn {
  cursor: pointer;
  font-family: 'Epilogue', sans-serif;
  font-size: 1vw;
  font-weight: bold;
  color: white;
  padding: 0 0.6vw 0 0.6vw;
  margin: 0 0 0 1vw;
  background-color: #24274A;
  border-radius: 1.1rem;
}

form div #password {
  margin: 0 0 2vh 0;
  padding: 0;
  border: 0.1px solid #C4C4C4;
  border-radius: 0.5rem;
  height: 4vh;
  width: 30vw;
  font-family: 'Epilogue', sans-serif;
}
</style>