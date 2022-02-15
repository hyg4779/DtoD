<template>
  <div class="itemdetail">
    <header>
      <h3 style="font-weight: bold;">{{title}}</h3>
      <div class="profileicon">
        <img v-if="itemuserImg" :src="itemuserImg"> 
        <img v-else src="../../assets/default_user.png">
      </div>
      <p>{{itemuserName}}</p>
    </header>

    <body>
      <section>
        <h6 class="p-0 ms-3 mt-2">기술 스택</h6>
        <span
          v-for="(item, idx) in getSkills"
          :key="idx"
        >
          {{item}}
        </span>
      </section>
      <br>
      <aside>
        <div class="img-box" :style="style">
          <img 
          v-for="(stack, idx) in imgs"
          :key="idx"
          :src="require(`@/assets/stacks/${stack}.png`)"
          alt="img"
          >
        </div>
        <ul>
          <li>
            <p>모집인원</p>
            <p>{{peopleCount}}명</p>
          </li>
          <li>
            <p>모집기간</p>
            <p>{{joinDate}}</p>
          </li>
          <li>
            <p>수행기간</p>
            <p>{{ingDate}}</p>
          </li>             
        </ul>
      </aside>

      <div>
        <ul>
          <li>
            <p>스터디 소개</p>
            <p v-html="getContent(this.content2)"></p>
          </li>
          <li>
            <p>스터디 규칙</p>
            <p v-html="getContent(this.content3)"></p>
          </li>
          <li>
            <p>오픈 카카오톡</p>
            <p v-html="getContent(this.content1)"></p>
          </li>
        </ul>
      </div>
    </body>

    <footer>
      <div v-if="this.token">
        <StudyComment 
          v-for="(comment, idx) in this.comments"
          :key="idx"
          :comment="comment"
          :item_pk="item_pk"
          @onParentDeleteComment="onParentDeleteComment"
        />
      </div>
      <hr v-if="this.comments.length !== 0" style="margin: 8px;">
      <form @submit="commentSubmit" v-if="this.token">
        <div class="form-group" style="margin-bottom:10px;">
          <textarea 
            class="form-control"
            placeholder="댓글을 남겨주세요" 
            id="comment" 
            rows="2" 
            v-model="mycomment" 
            @keypress.enter="commentSubmit"
            >
          </textarea>
          <button class="myBtn submit" id="sub">등록</button>
        </div>
      </form>
      <!-- <div class="commentprofilebox" v-if="this.token">
        <div class="commentprofileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../assets/default_user.png">
        </div>
        <div class="commentprofilename">{{userName}}</div>
      </div> -->
    </footer>
    
  </div>
</template>

<script>
import { api } from '../../../api.js'
import axios from 'axios'
import StudyComment from "../../components/board/studyboard/StudyComment.vue"

export default {
  name: 'ItemDetail',
  components: {
    StudyComment,
  },
  props: {
    item: Object
  },
  data() {
    return {
      token: '',
      item_pk: this.item.sboardId,

      title: '',
      tech:'',
      content1: '',
      content2: '',
      content3: '',
      joinDate: '',
      ingDate: '',
      peopleCount: 0,
      imgPath: '',
      imgs: null,
      style: {
        backgroundColor: this.imgPath
      },

      itemuserName: '',
      itemuserImg: '',
      itemuserEmail: '',
      userImg: '',
      userName: '',

      mycomment: '',
      comments: [],
    }
  },
  computed: {
    getSkills: function() {
      const t = this.tech
      const temp = t.split(',')
      let res = []
      for(let i = 0; i < temp.length; i++){
        res.push(temp[i])
      }
      return res
    },
  },
  methods:{
    getContent(content) { 
      return content.split('\n').join('<br>'); 
    },
    commentSubmit(event) {
      event.preventDefault()
      if (this.mycomment.length !== 0) {
        const item_pk = this.item.sboardId
        const token = localStorage.getItem('jwt')
        axios({
          url: api.CREATE_STUDY_BOARD_COMMENT + `${item_pk}`,
          method: 'POST',
          data: {
            scommentContent: this.mycomment
          },
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then(()=>{
          // console.log(res.data)
          axios({
            url: api. GET_STUDY_BOARD_COMMENT + `${item_pk}`,
            method: 'GET',
            headers: {
              Authorization: 'Bearer ' + token
            },
          }).then((res)=>{
              const temp = []
              res.data.forEach((element)=>{
                temp.push(element)
              })
              this.comments = temp
          }).catch((err)=>{
            console.error(err)
          })
        }).catch((err)=>{
          console.error(err)
        })
        this.mycomment = ''
      } 
      else {
        alert("댓글을 입력하세요.")
      }
    },
    onParentDeleteComment() {
      const item_pk = this.item.sboardId
      const token = localStorage.getItem('jwt')
      axios({
        url: api.GET_STUDY_BOARD_COMMENT + `${item_pk}`,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
          const temp = []
          res.data.forEach((element)=>{
            temp.push(element)
          })
          this.comments = temp
      }).catch((err)=>{
        const temp = []
        this.comments = temp
        console.error(err)
      })
    },
  },
  created() {
    // console.log(this.item)
    
    this.itemuserEmail = this.item.user.userEmail
    this.itemuserImg = this.item.user.userImg
    this.itemuserName = this.item.user.userName
    this.imgPath = this.item.sboardImg
    this.title = this.item.sboardTitle
    this.peopleCount = this.item.sboardPerson
    this.ingDate = this.item.sboardIngdate
    this.joinDate = this.item.sboardJoindate
    this.tech = this.item.sboardTechstack
    this.content1 = this.item.sboardContent1
    this.content2 = this.item.sboardContent2
    this.content3 = this.item.sboardContent3
    
    let stacks = this.item.sboardTechstack
    // 배열로 저장
    let result = stacks.split(',')
    // console.log(result.length)

    // 기술이 4개 이상이면 3개만 담고 그 이하는 다 담기
    if(result.length >= 4){
      // console.log(result.slice(0,3))
      this.imgs = result.slice(0,3)
    }else{
      this.imgs = result
    }
    this.style.backgroundColor = this.item.sboardImg
    // console.log(this.imgPath)
    // console.log(this.style.backgroundColor)

    const token = localStorage.getItem('jwt')
    this.token = token
    
    axios({
      url:  api.USER_INFO_GET,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.userName = res.data.userName
      this.userImg = res.data.userImg
    }).catch((err)=>{
      console.error(err)
    })

    axios({
      url: api.GET_STUDY_BOARD_COMMENT + `${this.item.sboardId}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      const temp = []
      res.data.forEach((element)=>{
        temp.push(element)
      })
      this.comments = temp
      // console.log(this.comments)
    }).catch((err)=>{
      console.error(err)
    })

  }
}
</script>

<style scoped>
hr{
  margin: 8px;
}
.itemdetail{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: auto;
  height:auto;
  padding:10px !important;
}

header{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 0 2.5vh 0;
}

header h3{
  font-weight: bold;
}

header p {
  font-size: 1rem;
  font-weight: bold;
}

.profileicon {
  margin: 2vh 1vw 2vh 1vw;
  width : 6.5vh;
  height : 6.5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.profileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}

body{
  background-color: #FFFFFF !important;
}

section {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  font-weight: 400;
  font-size: 1vw;
}

section span {
  border: 1px solid #F0F0F0;
  border-radius: 8rem;
  padding: 0.3vh 0.6vw 0.2vh 0.6vw;
  margin: 0 0 0 1vw;
  background-color: #F0F0F0;
  font-family: 'Epilogue', sans-serif;
}

aside {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin:  0.6vh 0 2vh 0;
}

.img-box{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin: 1rem;
  padding: 1rem;
  width: 20rem;
  height: 15rem;
  border-radius: 2rem;
  box-shadow: 0.1rem 0.1rem 0.1rem rgba(0, 0, 0, 0.25);
}

.img-box img{
  margin: .5rem;
  width: 5rem;
  height: 5rem;
}


ul{
  list-style-type: none;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-right: 10rem;
  padding-left: 1rem;
  /* height: 15rem; */
}

ul p:nth-child(1){
  font-weight: bold;
  font-size: 1.1vw;
}



.form-group .form-control{
  /* background-color: black; 
  color:white; */
  border-radius: 1rem;
  height: 10vh;
  font-family: 'Epilogue', sans-serif;
}

.commentprofileicon {
  margin: 0 1vw 2vh 0;
  width : 5vh;
  height : 5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}

.btnGroup{
  display: flex;
}

.myBtn {
  border-radius: 1rem;
  color: #FFFFFF;
  cursor: pointer;
  font-size: 0.8vw;
  font-weight: bold;
  margin: 0 0.3vw 0 0.3vw;
  padding: 0 0.5vw 0.1vh 0.5vw;
  transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
  touch-action: manipulation;
  will-change: transform;
}


.myBtn:hover {
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.myBtn:active {
  box-shadow: none;
  transform: translateY(0);
}

form {
  position: relative;
}
#sub {
  background-color: rgb(50,100,250);
  position: absolute;
  right: 0.5vw;
  bottom: 1vh;
  padding: 0.4vh 0.6vw 0.5vh 0.6vw;
}
</style>
