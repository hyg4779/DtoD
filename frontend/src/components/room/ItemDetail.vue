<template>
  <div class="itemdetail">
    <div id="left">
      <header class="mt-2">
        {{this.item.title}}
      </header>
      <ul class="mt-4">
        <li>
          user_id: {{this.item.user_id}}
        </li>
        <li>
          개설날짜: {{this.item.startDate}}
        </li>
        <li>
          마감날짜: {{this.item.endDate}}
        </li>
        <li>
          현재인원 / 모집인원: {{}}
        </li>
        <li>
          비밀번호
          <input id="pwd" type="password"/>
        </li>
      </ul>
    </div>

    <div id="right">
      <p>📌 사용스텍</p>
      <div
        class="imgBox"
      >
        <img
          v-for="(skill, idx) in this.item.stacks"
          :key="idx"
          class="mt-2"
          :src="require(`../../assets/stacks/${skill}.png`)"
          alt="stack_logo"
        >
      </div>
      <ul>
        <li>
          설명: {{ this.item.content }}
        </li>
        <li>
          오픈카카오톡: {{ this.item.kakaoCode }}
        </li>
      </ul>
      <footer>
        <button id="applyBtn" >신청</button>
        <button id="cancelBtn" >닫기</button>
      </footer>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'ItemDetail',
  props: {
    item: Object,
  },
}
</script>

<style scoped>
.itemdetail{
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#left{
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 100%; 
  margin: 10px;
}

#left header {
  font-size: 3em;
  font-weight: bold;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  padding: 0 0 0 1rem;
  width: 80%;
}

#left ul{
  list-style-type: square;
}

#left li {
  font-size: 1.4rem;
  font-weight: bold;
  padding: 0.3rem;
  margin: .5rem 0 .5rem 0;
}

#pwd{
  border-radius: .5rem;
  box-shadow: 0.1rem 0.1rem 0.1rem rgb(150,150,150);
  margin: 1rem 0 1rem 0;
}

#right{
  width: 100%;
  margin: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

#right p {
  font-size: 1.5rem;
  font-weight: bold;
}

.imgBox{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}

#right img {
  width: 5rem;
  height: 5rem;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  padding: 2%;
}

#right ul{
  list-style-type: square;
}

#right li {
  font-size: 1.4rem;
  font-weight: bold;
  padding: 0.3rem;
  margin: .5rem 0 .5rem 0;
}

footer{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}

footer button{
  font-size: 1.5rem;
  font-weight: bold;
  padding: .5rem 1em .5rem 1em;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
}

footer button:nth-child(1){
  background-color: rgb(153, 177, 255);
}

</style>