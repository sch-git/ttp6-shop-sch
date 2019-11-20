<template>
  <div>
    <!--第二导航区-->
    <el-menu
      active-text-color="#fff"
      background-color="#5AA4EE"
      class="el-menu-demo el-menu"
      mode="horizontal"
      text-color="#fff">
          <el-menu-item  @click="handleChangeHome"><i class="el-icon-s-home"></i>主页</el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              {{ this.$store.state.category.name }}
            </template>
            <el-menu-item @click="handleChangeCategory({'id':'','name':'所有分类'})" v-if="this.$store.state.category.name!=='所有分类'"
                          v-text="'所有分类'"></el-menu-item>
            <el-menu-item :key="category.id" @click="handleChangeCategory(category)" v-for="category in categoryList"
                          v-text="category.name"></el-menu-item>
          </el-submenu>
          <el-dropdown style="float:right;padding-left: 15px" v-if="user!==null">
            <div>
              <el-avatar :src=defaultHeadImageUrl class="avatar" fit="fill"
                         size="medium"></el-avatar>
              <span class="avatar" style="text-align: center">{{ user.name }}</span>
            </div>
            <el-dropdown-menu slot="dropdown">
              <router-link to="/home/userInfo">
                <el-dropdown-item>个人中心</el-dropdown-item>
              </router-link>
              <router-link to="/home/order">
                <el-dropdown-item>我的订单</el-dropdown-item>
              </router-link>
              <router-link to="/home/car">
                <el-dropdown-item>我的购物车</el-dropdown-item>
              </router-link>
            </el-dropdown-menu>
          </el-dropdown>
          <el-dropdown style="float:right;padding-left: 15px" v-if="user==null">
            <div>
              <el-avatar :src=defaultHeadImageUrl class="avatar" fit="fill"
                         size="medium"></el-avatar>、
              <router-link to="/login">
                <span class="avatar" style="text-align: center">前往登录</span>
              </router-link>
            </div>
          </el-dropdown>
    </el-menu>
    <!--分类展示区-->
    <el-row :gutter="12" style="padding-left: 50px;padding-right: 50px;margin-top: 15px" v-if="!showFlag">
      <el-col :span="24">
        <el-card :key="category.id" class="box-card"
                 style="width: 100%;background: #f3f4f6;margin-bottom: 15px" v-for="category in categoryList">
          <div class="clearfix" slot="header">
            <span style="font-weight: bolder;font-size: 18px" v-text="category.name">卡片名称</span>
          </div>
          <el-row>
            <el-col :key="good.id" :offset="index > 0 ? 1 : 0" :span="4"
                    v-for="(good, index) in category.goodList">
              <el-card :body-style="{ padding: '0px' }">
                <el-image
                  :src="good.url"
                  class="image"
                  fit="scale-down">
                  <div class="image-slot" slot="error">
                    <el-image :src="good.url"></el-image>
                  </div>
                </el-image>
                <div style="padding: 14px;">
                  <span style="font-weight: bolder;font-size: 13px" v-text="good.name">加载出错</span>
                  <div class="bottom clearfix">
                    <span style="color: #ff5900;font-size: 13px" v-text="'价格: $'+good.price">加载出错</span>
                    <el-button @click="handleGoodInfo(good.id)" class="button" icon="el-icon-shopping-cart-2"
                               type="text">立即购买</el-button>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--查询商品结果-->
    <el-row style="padding-left: 50px;padding-right: 50px;margin-top: 55px" v-if="showFlag">
      <el-col :key="good.id" :offset="index%5!== 0 ? 1 : 0" :span="4"
              @click="handleGoodInfo(id)" v-for="(good, index) in goodList">
        <el-card>
          <el-image
            :src="good.url"
            class="image"
            fit="scale-down">
            <div class="image-slot" slot="error">
              <el-image :src="good.url"></el-image>
            </div>
          </el-image>
          <div style="padding: 14px;">
            <span style="font-weight: bolder;font-size: 13px" v-text="good.name">加载出错</span>
            <div class="bottom clearfix">
              <span style="color: #ff5900;font-size: 13px" v-text="'价格: $'+good.price">加载出错</span>
              <el-button @click="handleGoodInfo(good.id)" class="button" icon="el-icon-shopping-cart-2"
                         type="text">立即购买</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      // 用户默认头像url
      defaultHeadImageUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      // 用户对象信息
      user: [{
        name: '',
        address: '',
        phone: '',
        email: '',
        headImageUrl: '',
        totalScore: ''
      }],
      // 所有拥有商品的父分类集合
      categoryList: [{
        id: '',
        name: '',
        goodList: [{
          id: '',
          name: '',
          stock: '',
          price: '',
          description: ''
        }]
      }],
      // 是否展示查询结果
      showFlag: false,
      // 搜索栏-商品
      goodName: '',
      // 每个分类下的商品集合
      goodList: [{
        id: '',
        name: '',
        stock: '',
        price: '',
        description: ''
      }]
    }
  },
  mounted () {
    this.categoryList = this.getCategoryGoodList()
    this.getUserInfo()
  },
  methods: {
    // 获取首页展示的分类和商品数据
    getCategoryGoodList () {
      const _this = this
      this.$axios.get('/lucky/good/list').then((res) => {
        if (res.data.code === 200) {
          _this.categoryList = res.data.data
        } else {
          _this.$message.error('请求失败!')
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    },
    // 查询条件变化或点击查询
    handleChangeCategory (category) {
      this.$store.commit('setCategory', category)
      this.getGoodList(category.id, this.goodName)
      this.$store.commit('setGoodList', this.goodList)
      this.showFlag = true
    },
    getGoodList (categoryId, goodName) {
      const _this = this
      let UserSearchGoodAO = {
        'id': categoryId,
        'name': goodName
      }
      this.$axios.post('/lucky/good/list', UserSearchGoodAO).then((res) => {
        _this.goodList = res.data.data
      })
    },
    handleChangeHome () {
      this.showFlag = false
      this.$store.commit('setCategory', { 'id': '', 'name': '所有分类' })
    },
    handleGoodInfo (id) {
      this.$router.push('goodInfo/' + id)
    },
    // 获取用户个人页信息
    getUserInfo () {
      const _this = this
      console.log('/lucky/user/info')
      this.$axios.get('/lucky/user/info').then((res) => {
        if (res.data.code === 200) {
          _this.user = res.data.data
          if (_this.user.headImageUrl.length > 0) {
            _this.defaultHeadImageUrl = _this.user.headImageUrl
          }
        } else if (res.data.code === 100) {
          _this.user = null
        }
      })
    }
  }
}
</script>

<style scoped>
  .el-menu {
    padding-left: 50px;
    padding-right: 50px;
    height: 60px;
    border: solid 1px #2C5782;
  }

  .el-dropdown {
    color: #fff;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 90%;
    height: 228px;
    display: block;
    margin: auto;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .avatar {
    line-height: 60px;
    vertical-align: middle;
  }
  .el-menu-item, .el-submenu__title{
    height: 60px;
    line-height: 60px;
  }
</style>
