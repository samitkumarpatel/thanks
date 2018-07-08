import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    memberId : null
  },
  mutations: {
    setMemberId(state,payload){
      state.memberId=payload;
    }
  },
  actions: {
    
  }
})
