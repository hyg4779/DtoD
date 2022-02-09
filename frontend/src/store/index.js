import Vue from 'vue'
import Vuex from 'vuex'
// import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  // plugins: [
  //   createPersistedState(),
  // ],  
  state: {
    credentials:{
      userEmail: null,
      userPwd: null,
      userName: null,
      userJobs: null,
      userTechstack: null,
    },
    date: {
      joindate: null,
      ingdate: null,
    }
  },
  mutations: {
    userCreate(state, payload){
      state.credentials.userEmail = payload.email
      state.credentials.userPwd = payload.pwd
    },
    nickName(state, payload){
      state.credentials.userName = payload.nickname
    },
    jobs(state, payload){
      state.credentials.userJobs = payload.jobs
    },
    skills(state, payload){
      state.credentials.userTechstack = payload.skills
    },

    joinDate(state, payload){
      state.date.joindate = payload.joindate
    },
    ingDate(state, payload){
      state.date.ingdate = payload.ingdate
    }
    // infoIntialize(state){
    //   for(let property in state.credentials){
    //     state.credentials[property] = null
    //     console.log(state.credentials)
    //   }
    // }

  },
  actions: {
    userCreate({commit}, payload){
      commit('userCreate', payload)
    },
    nickName({commit}, payload){
      commit('nickName', payload)
    },
    jobs({commit}, payload){
      commit('jobs', payload)
    },
    skills({commit},payload){
      commit('skills', payload)
    },

    joinDate({commit}, payload){
      commit('joinDate', payload)
    },
    ingDate({commit}, payload){
      commit('ingDate', payload)
    }
    // infoIntialize({commit}){
    //   commit('infoIntialize')
    // }

  },
  modules: {
  }
})
