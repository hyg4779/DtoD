import { createStore } from 'vuex'

export default createStore({
  state: {
    user_info:{
      email: null,
      pwd: null,
      nick_name: null,
      jobs: null,
      stacks: null,
    }
  },
  mutations: {
    userCreate(state, payload){
      state.user_info.email = payload.email
      state.user_info.pwd = payload.password
    },
    nickName(state, payload){
      state.user_info.nick_name = payload
    },
    jobs(state, payload){
      state.user_info.skills = payload
    },
    stacks(state, payload){
      state.user_info.stacks = payload
    },
    infoIntialize(state){
      for(let property in state.user_info){
        state.user_info[property] = null
      }
    }

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
    stacks({commit},payload){
      commit('stacks', payload)
    },
    infoIntialize({commit}){
      
      commit('infoIntialize')
    }


  },
  modules: {
  }
})
