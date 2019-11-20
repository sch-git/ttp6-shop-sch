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
                     size="medium">
          </el-avatar>
          <span class="avatar" style="text-align: center">{{ user.name }}</span>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/home/userInfo">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item>我的订单</el-dropdown-item>
          <router-link to="/home/car">
            <el-dropdown-item>我的购物车</el-dropdown-item>
          </router-link>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>
    <!--步骤条-->
    <el-row style="margin-top: 35px">
      <el-col :offset="2" :span="20">
        <el-steps :active="active" align-center finish-status="success">
          <el-step title="步骤 1"></el-step>
          <el-step title="步骤 2"></el-step>
          <el-step title="步骤 3"></el-step>
        </el-steps>
      </el-col>
    </el-row>
    <!--确认订单-->
    <el-row style="margin-top: 25px" v-if="active===0">
      <el-col :offset="5" :span="14">
        <el-card :key="order.id" class="box-card" v-for="order in orderItemList">
          <el-row style=" height: 100px">
            <el-col :span="5">
              <el-image :src="order.url" class="image" fit="scale-down"></el-image>
            </el-col>
            <el-col :span="5" style="line-height: 25px">
              <p>商品名</p>
              {{order.name}}
            </el-col>
            <el-col :span="5" style="line-height: 25px">
              <p>单价</p>
              {{order.price}}
            </el-col>
            <el-col :span="5" style="line-height: 25px">
              <p>数量</p>
              {{order.number}}
            </el-col>
            <el-col :span="4" style="line-height: 25px">
              <p>小计</p>
              {{order.number*order.price}}
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row v-if="active===0">
      <el-col :offset="5" :span="14">
        <el-card class="box-card">
          <el-row>
            <el-col :offset="20" :span="1">
              <span style="font-weight: bolder">结算</span>
            </el-col>
            <el-col :span="3">
              <span style="font-weight: bolder;color: #ff0036">￥{{totalPrice}}.00</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--填写信息-->
    <el-row style="margin-top: 25px" v-if="active===1">
      <el-col :offset="5" :span="14">
        <el-card class="box-card">
          <div class="clearfix" slot="header">
            <span style="font-weight: bolder;line-height:40px">填写收货信息</span>
            <el-button @click="saveForm('formReceiver')" plain style="float: right;" type="success" v-show="!editInfo">
              保存
            </el-button>
            <el-button @click="editInfo=true" plain style="float: right;margin-right: 10px" type="danger"
                       v-show="!editInfo">取消
            </el-button>
            <el-button @click="editInfo=false" plain style="float: right;margin-right: 10px" type="success"
                       v-show="editInfo">编辑
            </el-button>
          </div>
          <div v-if="editInfo">
            <el-row>
              <el-col :span="3" class="text item" style="font-weight: bolder">
                <span style="float: right">收货人姓名：</span>
              </el-col>
              <el-col :span="3" class="text item" style="font-weight: bolder">
                <span>{{formReceiver.name}}</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="3" class="text item" style="font-weight: bolder">
                <span style="float: right">收货人手机号：</span></el-col>
              <el-col :span="3" class="text item" style="font-weight: bolder">
                <span>{{formReceiver.phone}}</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="3" class="text item" style="font-weight: bolder">
                <span style="float: right">收货地址：</span></el-col>
              <el-col :span="3" class="text item" style="font-weight: bolder">
                <span>{{formReceiver.address}}</span>
              </el-col>
            </el-row>
          </div>
          <!--修改-->
          <div v-if="!editInfo">
            <el-form :model="formReceiver" :rules="rules" label-position="right" label-width="120px" ref="formReceiver">
              <el-form-item label="收货人姓名" prop="name">
                <el-input auto-complete="off" v-model="formReceiver.name"></el-input>
              </el-form-item>
              <el-form-item label="收货人手机号" prop="phone">
                <el-input auto-complete="off" v-model="formReceiver.phone"></el-input>
              </el-form-item>
              <el-form-item>
                <div class="block">
                  <el-cascader
                    :options="citys"
                    :props="{ expandTrigger: 'hover' }"
                    @change="handleChange"
                    ref="cascaderAddress"
                  ></el-cascader>
                </div>
              </el-form-item>
              <el-form-item label="收货地址" prop="address">
                <el-input auto-complete="off" v-model="formReceiver.address"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!--付款-->
    <el-row>
      <el-col :offset="16" :span="4">
        <el-button @click="cancelOrder" plain style="margin-top: 12px;" type="danger">取消</el-button>
        <el-button @click="next" plain style="margin-top: 12px;" type="success"
                   v-text="active>=2?'完成支付':'确认'"></el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    import {CodeToText} from 'element-china-area-data'

    export default {
  name: 'Order',
  data () {
    return {
      city: '',
      orderItemList: [],
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
      active: 0,
      orderId: '',
      orderIdTest: 0,
      totalPrice: 0,
      editInfo: true, // 编辑按钮
      formReceiver: {
        'name': '',
        'phone': '',
        'address': ''
      }, // 收货人信息
      citys: require('../../../static/citys.json'),
      rules: {
        phone: [{ required: true, message: '请输入收货人手机号', trigger: 'blur' }],
        name: [{ required: true, message: '请输入收货人姓名', trigger: 'blur' }],
        address: [{ required: true, message: '请输入收货地址', trigger: 'blur' }]
      }
    }
  },
  created () {

  },
  mounted () {
    this.orderItemList = this.$store.state.multipleSelection
    this.countTotalPrice()
    this.getReceiver()
    this.getUserInfo()
  },
  methods: {
    next () {
      if (this.editInfo === true) {
        if (this.active === 1 && this.formReceiver.address === null) {
          this.$message.error('请填写收货地址!')
          return
        }
        if (this.active++ >= 2) {
          // 完成订单
          this.$message.success('恭喜,订单已完成!')
          this.successPay(this.orderId)
          this.$router.push('order')
        }
        if (this.active === 2) {
          this.createOrder()
        }
      } else {
        this.$message.error('请先保存信息!')
      }
    },
    // 取消订单支付
    cancelOrder () {
      this.$confirm('确认取消订单?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        this.$message.success('取消订单成功!')
        if (this.active === 2) {
          this.cancelPay(this.orderId)
        }
        this.$router.push('car')
      })
    },
    // 保存收货信息
    saveForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message.success('保存成功!')
          this.editInfo = !this.editInfo
        } else {
          this.$message.error('请填写完整信息!')
          return false
        }
      })
    },
    handleChange (value) {
      this.city = ''
      value.map((addrCode) => {
        this.city += CodeToText[addrCode]
      })
    },
    // 计算总价
    countTotalPrice () {
      this.totalPrice = 0
      for (let item of this.orderItemList) {
        this.totalPrice += (item.price * item.number)
      }
    },
    // 获取收货人信息
    getReceiver () {
      const _this = this
      this.$axios.get('/lucky/receiver').then((res) => {
        if (res.data.code === 200) {
          _this.formReceiver = res.data.data
        } else {
          this.$message.error('请求出错!')
        }
      }).catch(() => {
        this.$message.error('请求出错!')
      })
    },
    // 创建订单
    createOrder () {
      const _this = this
      let orderAO = {
        'address': this.city + this.formReceiver.address,
        'receiver': this.formReceiver.name,
        'phone': this.formReceiver.phone,
        'totalPrice': this.totalPrice
      }
      // 创建订单
      this.$axios.post('/lucky/order/order', orderAO).then((res) => {
        console.log(res.data)
        if (res.data.code === 200) {
          _this.orderId = res.data.data
          _this.orderIdTest = res.data.data
          _this.createOrderItem()
        } else {
          _this.$message.error('创建订单失败!')
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    },
    // 创建订单项
    createOrderItem () {
      const _this = this
      let orderItemAOList = []
      this.orderItemList.map((item) => {
        orderItemAOList.push({
          'orderId': this.orderId,
          'goodId': item.id,
          'goodName': item.name,
          'number': item.number,
          'price': item.price,
          'url': item.url
        })
      })
      console.log('创建订单项' + this.orderItemList)
      this.$axios.post('/lucky/order/item', orderItemAOList).then((res) => {
        if (res.data.code === 200) {
        } else {
          _this.$message.error('创建订单失败!!')
        }
      }).catch(() => {
        _this.$message.error('请求出错!!')
      })
    },
    // 取消支付
    cancelPay (orderId) {
      const _this = this
      const __this = this
      this.$axios.get('/lucky/order/item', {
        params: {
          'orderId': orderId
        }
      }).then((res) => {
        if (res.data.code === 200) {
          _this.orderItemList = res.data.data
          let orderItemAO = []
          _this.orderItemList.map((item) => {
            orderItemAO.push({
              'orderId': orderId,
              'goodId': item.id,
              'number': item.number
            })
          })
          _this.$axios.put('/lucky/order/order', orderItemAO).then((res) => {
            if (res.data.code === 200) {
              __this.$message.success(res.data.msg)
            } else {
              __this.$message.error(res.data.msg)
            }
          }).catch(() => {
            __this.$message.error('请求出错!')
          })
        } else {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    },
    // 完成支付
    successPay (orderId) {
      const _this = this
      let params = new URLSearchParams()
      params.append('id', orderId)
      params.append('score', Math.ceil(this.totalPrice / 100))
      this.$axios.put('/lucky/order/success', params).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
        } else {
          _this.$message.error('请求失败!')
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
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

  .image {
    width: 70%;
    height: 100px;
    display: block;
    margin: auto;
  }

  .avatar {
    line-height: 60px;
    vertical-align: middle;
  }

  .box-card {
    margin-bottom: 25px;
  }

  .el-menu-item, .el-submenu__title {
    height: 60px;
    line-height: 60px;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
