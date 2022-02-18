<template>
  <div class="mystudy">
    <div v-if="!session">
    <br>
      <div class="madebyme" v-if="this.madeitems">
        <div class="title">
          내가 만든 스터디
        </div>
        <div class="allitems">
          <MyItems
          :items="this.madeitems"
          />
        </div>
      </div>
      <br>
      <br>
      <div class="joinedstudy" v-if="this.joineditems">
        <div class="title">
          내가 참여하는 스터디
        </div>
        <div class="allitems">
          <Items
          @open-video="joinSession()"
          :items="this.joineditems"
          />
        </div>
      </div>
    </div>

    <div v-else id="Video">
      <header>
        <div>
          {{ videoInfo.roomTitle }}
        </div>

        <div>
          <button @click="leaveSession" id="leaveBtn">퇴실</button>
        </div>
      </header>
		
      <section>
        <!-- <div id="main-video" class="col-md-6">
          <UserVideo
            :stream-manager="mainStreamManager"
          />
        </div> -->
        <b-container class="bv-example-row">
          <b-row>

            <b-col id="videoBox" col-6>
              <UserVideo
                :stream-manager="publisher"
                @click.native="updateMainVideoStreamManager(publisher)"
              />
            </b-col>

            <b-col
              col-6
              id="videoBox"
              v-for="sub in subscribers"
              :key="sub.stream.connection.connectionId"
            >
              <UserVideo
                :stream-manager="sub"
                @click.native="updateMainVideoStreamManager(sub)"
              />
            </b-col>

          </b-row>
        </b-container>          
      </section>

      <footer>
        <div>
          채팅
        </div>
        <article>
          <div
            id="msgBox"
            v-for="(msg, idx) in ourMsg"
            :key="idx"
          >
            <h6>{{ msg.name }}</h6>
            <h6 class="m-0">{{ msg.data }}</h6>
          </div>
        </article>
        <div id="sub">
          <input
            type="text"
            v-model="sendData"
            @keypress.enter="send"
            class="m-0 p-1"
          >
          <button @click="send">전송</button>
        </div>
      </footer>
    </div>
  </div>
</template>
<script>
// import { dummy } from "../../../generated.js";
import MyItems from '../../components/profile/madebyme/MyItems.vue'
import Items from '../../components/profile/joined/Items.vue'
import UserVideo from '../../components/Video/UserVideo.vue';

import { api } from '../../../api.js'
import axios from 'axios'
import{ mapState } from 'vuex'
import { OpenVidu } from 'openvidu-browser';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://i6b210.p.ssafy.io:5443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {
  name: 'MyStudy',
  components: {
    MyItems,
    Items,
    UserVideo,
  },
  data () {
    return {
      username:'',

      madeitems: [],
      joineditems: [],

      OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

      sendData: null,   // 보내는 메세지
      receiveMsg: [],   // 수신한 메세지
    }
  },
  computed:{
    ...mapState([
      'videoInfo'
      /*
        roomContent1: 전화번호
        roomContent2: 소개
        roomId: 고유id
        roomImg: 
        roomIngdate: 
        roomIngday: 
        roomPerson: 모집인원
        roomPwd: 
        roomTechstack: 
        roomTime: 
        roomTitle: 
        user: Object
        authorities:authorities: Array(1)
        0:
        authorityName: "ROLE_USER"
        userEmail: "sok8079@naver.com"
        userImg: "https://firebasestorage.googleapis.com/v0/b/dtod-image-upload.appspot.com/o/dongchul.png?alt=media&token=2a620f7a-ce1f-4a81-8875-eb6f86eb27f8"
        userJobs: "프론트엔드 엔지니어"
        userName: "관리자이동철"
        userTechstack: "javascript,react,django,vue,pyt
      */
      ]),
      ourMsg(){
        const result = [...this.receiveMsg].reverse()
        if(result.length <= 8) {
          return result
        }else{
          return result.slice(0,8)
        }
      }
  },

  methods: {
    // 메세지 보내기
    send(event) {
      console.log(event.target.value)
      
      this.session.signal({
          data: this.sendData,
          to: [],
          type: "my-chat",
      }).catch((error) => {
          console.error(error);
      });
      this.sendData = null
    },
    
    // 화상회의 만들기
    joinSession () {      
      // OpenVidu 객체 생성 ---
			this.OV = new OpenVidu();

      // 세션 초기화
			this.session = this.OV.initSession();

      // 세션 이벤트가 발생할 때 수행할 작업 지정

      // 수신된 새 스트림마다 subscribers에 추가
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

      // 스트림이 없어지면
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

      // 비동기 오류가 나면
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});



      // 채팅 보내기
      this.session.signal({
        data: this.sendData,  // 보낼 메세지
        to: [],               // 대상 (default: everyone) 
        type: 'my-chat'      // The type of message (optional)
      })
      .then(() => {
          console.log('Message successfully sent');
      })
      .catch(error => {
          console.error(error);
      });

      // 채팅 수신하기
      this.session.on('signal:my-chat', (event) => {
        console.log(event.data); // Message
        console.log(event.from); // Connection object of the sender
        console.log(event.type); // The type of message ("my-chat")
        const { clientData } = JSON.parse(event.from.data)
        this.receiveMsg.push({
          name: clientData,
          data: event.data,
          time: event.from.creationTime})
      });


      // 유효한 사용자 토큰을 사용하여 세션에 연결

      // getToken: 서버에서 수행할 작업을 시뮬레이션 한 것.
      // token은 백엔드에서 받아와야 한다
      const roomToken = this.videoInfo.roomId.toString()
			this.getToken(roomToken).then(token => {
				this.session.connect(token, { clientData: this.username })
					.then(() => {

            // 원하는 속성을 가진 고유한 카메라 스트림 가져오기

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // 오디오 / 마이크 없을 때: undefined
							videoSource: undefined, // 캠. 캠 없을 때: undefined
							publishAudio: false,  	// 시작시 오디오 true/false 여부 
							publishVideo: false,  	// 시작시 캠 true/false 여부
							resolution: '640x360',  // 비디오 해상도
							frameRate: 30,			// 초당프레임
							insertMode: 'APPEND',	// 캠 영상이 video태그에 삽입되는 방법
							mirror: false       	// 거울모드 true/false 여부
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;

            // 스트림 게시
						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

    // 세션종료 메서드
		leaveSession () {
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;
      this.receiveMsg = [];
      this.sendData = null;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

    // 세션 토큰 가져오기
		getToken (mySessionId) {
      return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

    // 세션 만들기
		createSession (sessionId) {
      return new Promise((resolve, reject) => {
        axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
            customSessionId: sessionId,
					}), {
            auth: {
              username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
            if (error.response.status === 409) {
              resolve(sessionId);
						} else {
              console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

    // 만든 세션의 토큰 만들기
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
        axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
            auth: {
              username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

    // 클릭한 스트림 메인 스트림으로 변경
    updateMainVideoStreamManager (stream) {
      if (this.mainStreamManager === stream){
        return}
    },
	},
  created() {
    if (localStorage.getItem('jwt')) {
      const token = localStorage.getItem('jwt')

      axios ({
        method: 'get',
        url: api.USER_INFO_GET,
        headers: { 
          Authorization: 'Bearer ' + token
        }
      }).then(res=>{
        // console.log(res)
        this.username = res.data.userName
        axios({
          url: api.GET_STUDY_ROOM,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          // console.log(res)
          for (let i=0; i < res.data.length; i++){
            if( this.username === res.data[i].user.userName) {
              this.madeitems.push(res.data[i])
            }
          }
        }).catch(err=>{
          console.log(err)
        })
      }).catch(error => {
        console.log(error)
      })


      axios({
        url: api.GET_MY_ROOM,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        // console.log(res)
        this.joineditems = res.data
      }).catch(err=>{
        console.error(err)
      })
    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
  },

  destroyed(){
    if (this.data.session) this.data.session.disconnect();
    this.data.session = undefined;
    this.data.mainStreamManager = undefined;
    this.data.publisher = undefined;
    this.data.subscribers = [];
    this.data.OV = undefined;
    this.data.receiveMsg = [];
    this.sendData = null;
  },

}
</script>

<style scoped>
.mystudy {
  display: flex;
  flex-direction: column;
}

.mystudy .title {
  font-size: 1.58vw;
  margin: 1vh 0 0 4vw;
  font-weight: bold;
  color: #0D1350;
}

#Video{
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

header {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
  align-items: center;
  background-color: #24292F;
  box-shadow: 0.1rem 0 0.1rem rgb(0, 0, 0);
  text-align: center;
  width: 13vw;
  min-height: calc(100vh - 7.498vh);
}

header div:nth-child(1){
	font-size: 1rem;
  font-weight: bold;
	width: 10vw;
	color: rgb(50, 50, 50);
  padding: .5rem 1rem .5rem 1rem;
	margin: 1.5rem 0 0 0;
	background-color: rgb(250, 250, 250);
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(0, 0, 0);	
}

header div button{
	font-size: 1.5rem;
  font-weight: bold;
	width: 10vw;
	color: #eeeeee;
  padding: .5rem 1rem .5rem 1rem;
	margin: 0 0 1.5rem 0;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(0, 0, 0);
}

#leaveBtn{
	background-color: rgb(50, 50, 50);
}

#shareBtn{
  background-color: rgb(70, 70, 200);
}

#stopBtn{
  background-color: rgb(200, 70, 70);
}

section {
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	justify-content: space-between;
  margin: auto;
}

#videoBox{
  display: flex;
  flex-direction: column;
  align-items: center;
}

footer{
  display : flex;
	flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 1.5rem;
  background-color: rgba(36, 41, 47, 0.8);
  box-shadow: -0.1rem 0 0.1rem rgb(0, 0, 0);
  width: 18vw;
  min-height: calc(100vh - 7.498vh);
}

footer > div:nth-child(1){
	font-size: 1.5rem;
  font-weight: bold;
  text-align: center;
	width: 10vw;
	color: rgb(50, 50, 50);
  padding: .5rem 1rem .5rem 1rem;
	margin: 0 0 0 0;
	background-color: rgb(250, 250, 250);
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(0, 0, 0);	
}

footer article{
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
  height: 77vh;
  width: 18vw
}

#msgBox{
  font-size: .5rem;
  background-color: #eeeeee;
  border-radius: 1vw;
  box-shadow: 0.1vw 0.1vh 0.1vw rgb(0, 0, 0);
  padding: .5vw;
  margin: .5vh 0 .5vh 0 ;
  width: 16vw;
}

#msgBox h6:nth-child(1){
  font-weight: bold;
}

footer input{
  background-color: #eeeeee;
  font-size: 1.6vh;
  text-align: start;
	width: 11vw;
  height: 5vh;
	color: rgb(50, 50, 50);
	background-color: rgb(250, 250, 250);
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(0, 0, 0);	
}

#sub{
  display: flex;
  justify-content: center;
}

#sub  button{
  font-size: 1vw;
  font-weight: bold;
  text-align: center;
	width: 3vw;
	color: rgb(50, 50, 50);
	margin: 0 0 0 0.3vw;
	background-color: rgb(250, 250, 250);
  border-radius: 1vw;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(0, 0, 0);	
}
</style>