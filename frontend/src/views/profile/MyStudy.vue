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
          @open-video="joinSession(payload)"
          :items="this.joineditems"
          />
        </div>
      </div>
    </div>

    <div v-else id="Video">
      <header>
        <div>
          스터디 룸
        </div>

        <button @click="leaveSession" id="leaveBtn">퇴실</button>
      </header>
		
      <body>
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
              <p class="d-flex m-0">
                <button class="mx-2" @click="videoToggle">{{ videoStatus }}</button>
                <button class="mx-2" @click="audioToggle">{{ audioStatus }}</button>
              </p>

            </b-col>
            <b-col
              col-6
              v-for="sub in subscribers"
              :key="sub.stream.connection.connectionId"
            >
              <UserVideo
                :stream-manager="sub"
                @click.native="updateMainVideoStreamManager(sub)"
              />
            </b-col>

            <!-- <div class="w-100"></div>
            
            <b-col>
              <div id="box"></div>
            </b-col>
            <b-col>
              <div id="box"></div>
            </b-col> -->
          </b-row>
        </b-container>

          
          
      </body>

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

			mySessionId: 'SessionA',

      openedVideo: null,
    }
  },
  computed:{
    videoStatus(){
      let screen = this.publisher.stream.videoActive
      if(screen){
        return '🖥'
      }return '🖥❌'
    },
    audioStatus(){
      let screen = this.publisher.stream.audioActive
      if(screen){
        return '🔊'
      }return '🔈❌'
    }
  },
  methods: {

    // subscriber.subscribeToAudio(audioEnabled); true to unmute the audio track, false to mute it
    // subscriber.subscribeToVideo(videoEnabled); true to enable the video, false to disable it

    // 사용자 비디오 on/off
    videoToggle(){
      const video = this.publisher.stream.videoActive
      if(video){
        this.publisher.publishVideo(false)
        console.log('video '+this.publisher.stream.videoActive)
      }else{
        this.publisher.publishVideo(true)
        console.log('video '+this.publisher.stream.videoActive)
        }
    },
    // 사용자 오디오 on/off
    audioToggle(){
      const audio = this.publisher.stream.audioActive
      if(audio){
        this.publisher.publishAudio(false)
        console.log('audio '+this.publisher.stream.audioActive)
      }else{
        this.publisher.publishAudio(true)
        console.log('audio '+this.publisher.stream.audioActive)
        }
    },
		joinSession (payload) {
      // emit으로 받은 스터디룸 정보 받아옴
      this.openedVideo = payload
      
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

      // 유효한 사용자 토큰을 사용하여 세션에 연결

      // getToken: 서버에서 수행할 작업을 시뮬레이션 한 것.
      // token은 백엔드에서 받아와야 한다
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.username })
					.then(() => {

            // 원하는 속성을 가진 고유한 카메라 스트림 가져오기

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // 오디오 / 마이크 없을 때: undefined
							videoSource: undefined, // 캠. 캠 없을 때: undefined
							publishAudio: true,  	// 시작시 오디오 true/false 여부 
							publishVideo: true,  	// 시작시 캠 true/false 여부
							resolution: '640x360',  // 비디오 해상도
							frameRate: 30,			// 초당프레임
							insertMode: 'APPEND',	// 캠 영상이 video태그에 삽입되는 방법
							mirror: true       	// 거울모드 true/false 여부
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

		leaveSession () {
			// 세션종료 메서드
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

			/*
			* --------------------------
			* SERVER-SIDE RESPONSIBILITY
			* --------------------------
			* 이러한 메서드는 OpenVidu Server에서 필수 사용자 토큰을 검색합니다.
			* 이 동작은 프로덕션의 서버 측에 있어야 합니다(사용).
			* API REST, openvidu-java-client 또는 openvidu-node-client):
			* 1) OpenVidu Server에서 세션 초기화(POST /openvidu/api/sessions)
			* 2) OpenVidu Server에 연결 만들기(POST /openvidu/api/sessions/<세션_ID>/연결)
			* 3) '연결'토큰은 Session.connect() 메서드에서 소비되어야 합니다.
			*/


		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
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

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
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
      }).catch(error => {
        console.log(error)
      })

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
  }
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
  background-color: #24292F;
  text-align: center;
  width: 13vw;
  min-height: calc(100vh - 7.498vh);
}

header div{
	font-size: 1rem;
  font-weight: bold;
	width: 10vw;
	color: rgb(50, 50, 50);
  padding: .5rem 1rem .5rem 1rem;
	margin: 1rem;
	background-color: rgb(250, 250, 250);
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(0, 0, 0);	
}

#leaveBtn{
	font-size: 1.5rem;
  font-weight: bold;
	width: 10vw;
	color: #eeeeee;
  padding: .5rem 1rem .5rem 1rem;
	margin: 1rem;
	background-color: rgb(50, 50, 50);
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(0, 0, 0);
}

body {
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	justify-content: space-between;
  margin: 7vh 13vw 7vh 3vw;
}

#box{
  width: 24rem;
  height: 16rem;
  margin: 1rem;
  border: 1px solid #24292F;
}

#videoBox{
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>