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
        <router-link to="/home/order">我的订单</router-link>
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
          <el-dropdown-item>我的订单</el-dropdown-item>
          <router-link to="/home/car">
            <el-dropdown-item>我的购物车</el-dropdown-item>
          </router-link>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>
    <!--标签栏-->
    <el-row>
      <el-col :offset="4" :span="16">
        <el-tabs @tab-click="handleClick" type="border-card">
          <el-tab-pane label="我的订单" name="0">
            <el-row style="height: 49px;width: 100%;line-height: 49px">
              <el-col :span="5">
                订单编号
              </el-col>
              <el-col :span="3">
                收货人
              </el-col>
              <el-col :span="3">
                手机号
              </el-col>
              <el-col :span="3">
                地址
              </el-col>
              <el-col :span="3">
                订单总价
              </el-col>
              <el-col :span="3">
                商品数量
              </el-col>
              <el-col :span="4">
                操作
              </el-col>
            </el-row>
            <el-collapse accordion v-model="activeName">
              <el-collapse-item :key="order.id" :name="order.id" v-for="order in orderList">
                <template slot="title">
                  <div @click="clickOrder(order.id)" style="height: 49px;width: 100%;line-height: 49px">
                    <el-row>
                      <el-col :span="5">
                        <el-tooltip :content="order.orderNo" placement="top">
                          <span>{{order.orderNo.slice(0,20)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="3">
                        {{order.receiver}}
                      </el-col>
                      <el-col :span="3">
                        {{order.phone}}
                      </el-col>
                      <el-col :span="3">
                        <el-tooltip :content="order.address" placement="top">
                          <span>{{order.address.slice(0,9)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="3">
                        {{order.totalPrice}}
                      </el-col>
                      <el-col :span="3">
                        {{order.number}}
                      </el-col>
                      <el-col :span="4" v-if="order.status===0">
                        <el-button disabled size="mini" type="danger">已取消</el-button>
                      </el-col>
                      <el-col :span="4" v-if="order.status===1">
                        <el-button @click="cancelOrder(order.id)" plain size="mini" type="danger">取消</el-button>
                        <el-button @click="completeOrder(order.id,order.totalPrice)" plain size="mini"  type="success">完成</el-button>
                      </el-col>
                      <el-col :span="4" v-if="order.status===2">
                        <el-button disabled size="mini" type="success">已完成</el-button>
                      </el-col>
                    </el-row>
                  </div>
                </template>
                <!--表格-->
                <el-main>
                  <el-table
                    :data="tableData"
                    style="width: 100%"
                    tooltip-effect="dark">
                    <el-table-column
                      align="center"
                      label="商品图片"
                      width="200">
                      <template slot-scope="scope">
                        <el-image
                          :src="scope.row.url"
                          fit="scale-down"
                          style="width: 100px; height: 100px"></el-image>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="商品名称"
                      width="200">
                      <template slot-scope="scope">{{ scope.row.name }}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="单价"
                      show-overflow-tooltip
                      width="140">
                      <template slot-scope="scope">{{ scope.row.price}}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="数量"
                      show-overflow-tooltip
                      width="160">
                      <template slot-scope="scope">
                        {{ scope.row.number}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="小计"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.price*scope.row.number}}.00
                      </template>
                    </el-table-column>
                  </el-table>
                </el-main>
              </el-collapse-item>
            </el-collapse>
          </el-tab-pane>
          <el-tab-pane label="已取消" name="1">
            <el-row style="height: 49px;width: 100%;line-height: 49px">
              <el-col :span="5">
                订单编号
              </el-col>
              <el-col :span="4">
                收货人
              </el-col>
              <el-col :span="4">
                手机号
              </el-col>
              <el-col :span="4">
                地址
              </el-col>
              <el-col :span="4">
                订单总价
              </el-col>
              <el-col :span="3">
                商品数量
              </el-col>
            </el-row>
            <el-collapse accordion v-model="activeName">
              <el-collapse-item :key="order.id" :name="order.id" v-for="order in orderList" v-show="order.status===0">
                <template slot="title">
                  <div @click="clickOrder(order.id)" style="height: 49px;width: 100%;line-height: 49px">
                    <el-row style="color: #ff0036">
                      <el-col :span="5">
                        <el-tooltip :content="order.orderNo" placement="top">
                          <span>{{order.orderNo.slice(0,20)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="4">
                        {{order.receiver}}
                      </el-col>
                      <el-col :span="4">
                        {{order.phone}}
                      </el-col>
                      <el-col :span="4">
                        <el-tooltip :content="order.address" placement="top">
                          <span>{{order.address.slice(0,9)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="4">
                        {{order.totalPrice}}
                      </el-col>
                      <el-col :span="3">
                        {{order.number}}
                      </el-col>
                    </el-row>
                  </div>
                </template>
                <!--表格-->
                <el-main>
                  <el-table
                    :data="tableData"
                    style="width: 100%"
                    tooltip-effect="dark">
                    <el-table-column
                      align="center"
                      label="商品图片"
                      width="200">
                      <template slot-scope="scope">
                        <el-image
                          :src="scope.row.url"
                          fit="scale-down"
                          style="width: 100px; height: 100px"></el-image>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="商品名称"
                      width="200">
                      <template slot-scope="scope">{{ scope.row.name }}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="单价"
                      show-overflow-tooltip
                      width="140">
                      <template slot-scope="scope">{{ scope.row.price}}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="数量"
                      show-overflow-tooltip
                      width="160">
                      <template slot-scope="scope">
                        {{ scope.row.number}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="小计"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.price*scope.row.number}}.00
                      </template>
                    </el-table-column>
                  </el-table>
                </el-main>
              </el-collapse-item>
            </el-collapse>
          </el-tab-pane>
          <el-tab-pane label="未支付" name="2">
            <el-row style="height: 49px;width: 100%;line-height: 49px">
              <el-col :span="5">
                订单编号
              </el-col>
              <el-col :span="4">
                收货人
              </el-col>
              <el-col :span="4">
                手机号
              </el-col>
              <el-col :span="4">
                地址
              </el-col>
              <el-col :span="4">
                订单总价
              </el-col>
              <el-col :span="3">
                商品数量
              </el-col>
            </el-row>
            <el-collapse accordion v-model="activeName">
              <el-collapse-item :key="order.id" :name="order.id" v-for="order in orderList" v-show="order.status===1">
                <template slot="title">
                  <div @click="clickOrder(order.id)" style="height: 49px;width: 100%;line-height: 49px">
                    <el-row >
                      <el-col :span="5">
                        <el-tooltip :content="order.orderNo" placement="top">
                          <span>{{order.orderNo.slice(0,20)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="3">
                        {{order.receiver}}
                      </el-col>
                      <el-col :span="3">
                        {{order.phone}}
                      </el-col>
                      <el-col :span="3">
                        <el-tooltip :content="order.address" placement="top">
                          <span>{{order.address.slice(0,10)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="3">
                        {{order.totalPrice}}
                      </el-col>
                      <el-col :span="3">
                        {{order.number}}
                      </el-col>
                        <el-col :span="4" v-if="order.status===1">
                          <el-button @click="cancelOrder(order.id)" plain size="mini" type="danger">取消</el-button>
                          <el-button @click="completeOrder(order.id,order.totalPrice)" plain size="mini" type="success">完成</el-button>
                        </el-col>
                    </el-row>
                  </div>
                </template>
                <!--表格-->
                <el-main>
                  <el-table
                    :data="tableData"
                    style="width: 100%"
                    tooltip-effect="dark">
                    <el-table-column
                      align="center"
                      label="商品图片"
                      width="200">
                      <template slot-scope="scope">
                        <el-image
                          :src="scope.row.url"
                          fit="scale-down"
                          style="width: 100px; height: 100px"></el-image>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="商品名称"
                      width="200">
                      <template slot-scope="scope">{{ scope.row.name }}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="单价"
                      show-overflow-tooltip
                      width="140">
                      <template slot-scope="scope">{{ scope.row.price}}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="数量"
                      show-overflow-tooltip
                      width="160">
                      <template slot-scope="scope">
                        {{ scope.row.number}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="小计"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.price*scope.row.number}}.00
                      </template>
                    </el-table-column>
                  </el-table>
                </el-main>
              </el-collapse-item>
            </el-collapse>
          </el-tab-pane>
          <el-tab-pane label="已完成" name="3">
            <el-row style="height: 49px;width: 100%;line-height: 49px">
              <el-col :span="5">
                订单编号
              </el-col>
              <el-col :span="4">
                收货人
              </el-col>
              <el-col :span="4">
                手机号
              </el-col>
              <el-col :span="4">
                地址
              </el-col>
              <el-col :span="4">
                订单总价
              </el-col>
              <el-col :span="3">
                商品数量
              </el-col>
            </el-row>
            <el-collapse accordion v-model="activeName">
              <el-collapse-item :key="order.id" :name="order.id" v-for="order in orderList" v-show="order.status===2">
                <template slot="title">
                  <div @click="clickOrder(order.id)" style="height: 49px;width: 100%;line-height: 49px">
                    <el-row style="color: lime">
                      <el-col :span="5">
                        <el-tooltip :content="order.orderNo" placement="top">
                          <span>{{order.orderNo.slice(0,20)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="4">
                        {{order.receiver}}
                      </el-col>
                      <el-col :span="4">
                        {{order.phone}}
                      </el-col>
                      <el-col :span="4">
                        <el-tooltip :content="order.address" placement="top">
                          <span>{{order.address.slice(0,10)}}</span>
                        </el-tooltip>
                      </el-col>
                      <el-col :span="4">
                        {{order.totalPrice}}
                      </el-col>
                      <el-col :span="3">
                        {{order.number}}
                      </el-col>
                    </el-row>
                  </div>
                </template>
                <!--表格-->
                <el-main>
                  <el-table
                    :data="tableData"
                    style="width: 100%"
                    tooltip-effect="dark">
                    <el-table-column
                      align="center"
                      label="商品图片"
                      width="200">
                      <template slot-scope="scope">
                        <el-image
                          :src="scope.row.url"
                          fit="scale-down"
                          style="width: 100px; height: 100px"></el-image>
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="商品名称"
                      width="200">
                      <template slot-scope="scope">{{ scope.row.name }}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="单价"
                      show-overflow-tooltip
                      width="140">
                      <template slot-scope="scope">{{ scope.row.price}}</template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="数量"
                      show-overflow-tooltip
                      width="160">
                      <template slot-scope="scope">
                        {{ scope.row.number}}
                      </template>
                    </el-table-column>
                    <el-table-column
                      align="center"
                      label="小计"
                      show-overflow-tooltip>
                      <template slot-scope="scope">
                        {{scope.row.price*scope.row.number}}.00
                      </template>
                    </el-table-column>
                  </el-table>
                </el-main>
              </el-collapse-item>
            </el-collapse>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Order',
  data () {
    return {
      // 用户默认头像url
      defaultHeadImageUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      // 当前展示的订单项数据
      tableData: [
        {
          name: '王小虎',
          url: '',
          price: 0,
          number: 0
        }
      ],
      user: [{
        name: '',
        address: '',
        phone: '',
        email: '',
        headImageUrl: '',
        totalScore: ''
      }],
      // 面板标题订单数据
      orderList: [],
      orderItemList: [],
      activeName: 0,
      // 每页几条数据
      pageSize: 10,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1
    }
  },
  methods: {
    handleClick (tab, event) {
      console.log(tab, event)
      console.log(tab.name)
    },
    // 获取订单面板数据
    getOrderList () {
      const _this = this
      this.$axios.get('/lucky/order/list').then((res) => {
        if (res.data.code === 200) {
          _this.orderList = res.data.data
        }
      })
    },
    // 根据订单id获取订单项详情
    clickOrder (orderId) {
      const _this = this
      this.$axios.get('/lucky/order/item', {
        params: {
          'orderId': orderId
        }
      }).then((res) => {
        if (res.data.code === 200) {
          _this.orderItemList = res.data.data
          _this.tableData = _this.orderItemList
        } else {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    },
    // 取消订单
    cancelOrder (orderId) {
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
              __this.getOrderList()
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
    // 完成订单
    completeOrder (orderId, score) {
      this.successPay(orderId, score)
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
    },
    // 完成支付
    successPay (orderId, score) {
      const _this = this
      let params = new URLSearchParams()
      params.append('id', orderId)
      params.append('score', score)
      this.$axios.put('/lucky/order/success', params).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
          _this.getOrderList()
        } else {
          _this.$message.error('请求失败!')
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    }
  },
  created () {

  },
  mounted () {
    this.getOrderList()
    this.getUserInfo()
  }
}
</script>

<style scoped>
  * {
    text-align: center
  }

  .el-menu {
    padding-left: 50px;
    padding-right: 50px;
    height: 60px;
    border: solid 1px #2C5782;
  }

  .avatar {
    line-height: 60px;
    vertical-align: middle;
  }
</style>
