import Vue from 'vue'
import Vuex from 'vuex'
import vuexAlong from 'vuex-along'

Vue.use(Vuex)

const state = {
  token: false, // 用户登录判断
  category: {
    'id': '',
    'name': '所有分类'
  },
  goodList: [], // 搜索结果页商品数据
  multipleSelection: [] // 购物车页所选商品
}

const mutations = {
  setToken (state, userToken) {
    state.token = userToken
  },
  setGoodList (state, goodList) {
    state.goodList = goodList
  },
  setCategory (state, category) {
    state.category = category
  },
  setMultipleSelection (state, multipleSelection) {
    state.multipleSelection = multipleSelection
  }
}

const plugins = [vuexAlong]
export default new Vuex.Store({
  state,
  mutations,
  plugins
})
