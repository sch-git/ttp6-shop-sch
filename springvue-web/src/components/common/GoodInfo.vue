<template>
  <div>
    <!--第二导航区-->
    <el-menu
      active-text-color="#fff"
      background-color="#5AA4EE"
      class="el-menu-demo el-menu"
      mode="horizontal"
      text-color="#fff">
      <el-menu-item>
        <router-link to="/home/main"><i class="el-icon-s-home"></i>主页</router-link>
      </el-menu-item>
      <el-menu-item>
        <router-link to="/home/order"><i class="el-icon-s-home"></i>我的订单</router-link>
      </el-menu-item>
      <el-menu-item>
        <router-link to="/home/car"><i class="el-icon-s-home"></i>我的购物车</router-link>
      </el-menu-item>
      <el-dropdown style="float:right;padding-left: 15px">
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
    </el-menu>
    <!--商品详情区-->
    <el-row :gutter="12">
      <el-col :offset="3" :span="6">
        <el-card :body-style="{ padding: '0px' }">
          <el-image :src="goodImage" class="image" fit="scale-down"></el-image>
        </el-card>
      </el-col>
      <el-col :span="13">
        <el-card>
          <div class="clearfix" slot="header">
            <span class="goodName" v-text="good.name">商品名称</span>
            <p style="margin-top: 15px;margin-bottom: 0">
              <span :key="category.id" style="margin-right: 15px" v-for="category in good.categoryList">
                {{category.name}}</span>
            </p>
          </div>
          <div class="clearfix item" slot="">
            <span class="goodName">基本属性</span>
          </div>
          <el-row class="text item">
            <el-col :span="2">
              <span>价格:</span>
            </el-col>
            <el-col :span="6">
              <span style="font-weight: bolder;color: #ff0036" v-text="'$'+good.price">$1234</span>
            </el-col>
          </el-row>
          <el-row class="text item">
            <el-col :span="2">
              <span>描述:</span>
            </el-col>
            <el-col :span="22">
              <p style="margin:0" v-text="good.description">商品描述</p>
            </el-col>
          </el-row>
          <el-row class="text item">
            <el-col :span="2">
              <span>数量:</span>
            </el-col>
            <el-col :span="2">
              <el-input-number :max="good.stock" :min="1" @change="handleChange" controls-position="right"
                               v-model="number"></el-input-number>
            </el-col>
          </el-row>
          <el-row class="text item">
            <el-button :disabled="good.stock<1" plain type="danger">立即购买</el-button>
            <el-button @click="addCarItem" icon="el-icon-shopping-cart-2" style="height: 39.6px"
                       :disabled="good.stock<1" type="danger">加入购物车
            </el-button>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'GoodInfo',
  data () {
    return {
      // 用户默认头像url
      defaultHeadImageUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      good: {
        id: '',
        name: '',
        stock: 1,
        price: '',
        description: '',
        urlList: [],
        categoryList: [{
          id: '',
          name: ''
        }]
      },
      goodImage: '',
      user: [{
        name: '',
        address: '',
        phone: '',
        email: '',
        headImageUrl: '',
        totalScore: ''
      }],
      number: ''
    }
  },
  create () {
  },
  mounted () {
    this.good.id = this.$route.params.id
    this.getGoodInfo(this.good.id)
    this.getUserInfo()
  },
  methods: {
    // 获取商品基本信息
    getGoodInfo (id) {
      const _this = this
      this.$axios.get('/lucky/good/info', {
        params: {
          'id': id
        }
      }).then((res) => {
        if (res.data.code === 200) {
          _this.good = res.data.data
          _this.goodImage = _this.good.urlList[0]
        } else {
          this.$message.error(res.data.msg)
        }
      }).catch(() => {
        this.$message.error('请求出错!')
      })
    },
    // 商品加入购物车
    addCarItem () {
      const _this = this
      let carItemAO = {
        'id': this.good.id,
        'name': this.good.name,
        'price': this.good.price,
        'number': this.number,
        'stock': this.good.stock,
        'url': this.good.urlList[0]
      }
      this.$axios.post('/lucky/car/item', carItemAO).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
          _this.$router.push('../car')
        } else {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('添加购物车出错!')
      })
    },
    // 商品数量改变
    handleChange (value) {
      console.log(value)
    },
    // 获取用户个人页信息
    getUserInfo () {
      const _this = this
      this.$axios.get('/lucky/user/info').then((res) => {
        _this.user = res.data.data
        if (_this.user.headImageUrl.length > 0) {
          _this.defaultHeadImageUrl = _this.user.headImageUrl
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

  .image {
    width: 90%;
    height: 370px;
    margin: auto;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .goodName {
    font-weight: bolder;
    font-size: 20px;
  }

  .avatar {
    line-height: 60px;
    vertical-align: middle;
  }

  .el-menu-item, .el-submenu__title {
    height: 60px;
    line-height: 60px;
  }
</style>
