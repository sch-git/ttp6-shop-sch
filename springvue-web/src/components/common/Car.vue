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
            <!--<el-dropdown-item @click="signOut">我的购物车</el-dropdown-item>-->
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>
    <el-row>
      <el-col :offset="3" :span="18" style="background: white">
        <el-container>
          <!--面包屑导航-->
          <el-header style="background: #f3f4f6;">
            <el-breadcrumb separator-class="el-icon-arrow-right" style="line-height: 60px;">
            </el-breadcrumb>
          </el-header>
          <!--购物车信息-->
          <el-row style="height: 60px;line-height: 60px;">
            <el-col :span="4"><span style="font-weight: bold;">购物车</span></el-col>
          </el-row>
          <hr/>
          <!--表格-->
          <el-main>
            <el-table
              :data="tableData"
              @selection-change="handleSelectionChange"
              ref="multipleTable"
              style="width: 100%"
              tooltip-effect="dark">
              <el-table-column
                type="selection"
                width="55">
              </el-table-column>
              <el-table-column
                align="center"
                label="商品图片"
                width="200">
                <template slot-scope="scope">
                  <el-image
                    :src="scope.row.url"
                    :fit=fit
                    style="width: 100px; height: 100px">
                    <div class="image-slot" slot="error">
                      <el-image :fit=fit
                                :src="scope.row.url"
                                style="width: 100px; height: 100px">
                      </el-image>
                    </div>
                  </el-image>
                </template>
              </el-table-column>
              <el-table-column
                align="center"
                label="商品名称"
                prop="name"
                width="200">
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
                  <el-input-number :max="scope.row.stock" :min="1"
                                   @change="handleChange(scope.row.number,scope.row.id)" controls-position="right"
                                   size="small" v-model="scope.row.number"></el-input-number>
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
              <el-table-column
                align="center"
                label="操作"
                show-overflow-tooltip>
                <template slot-scope="scope">
                  <el-button @click="handleDelete(scope.$index, scope.row)" type="danger">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-main>
          <!--结算栏-->
          <hr style="margin-bottom: 0"/>
          <el-row style="background: #fffeee;height: 60px;">
            <el-col :span="4"><span style="font-weight: bold;line-height: 60px">结算已选
            <span v-text="multipleSelection.length">0</span>件</span></el-col>
            <el-col :offset="12" :span="4" style="line-height: 60px"><span style="font-weight: bold;">实付款:
            <span style="color: red" v-text="totalPrice">0.00000</span></span></el-col>
            <el-col :span="4">
              <el-button @click="handleSubmitOrder" size="medium" style="width: 190px;height: 60px;border-radius:0"
                         type="info">
                <span style="font-weight: bolder;">提交订单</span>
              </el-button>
            </el-col>
          </el-row>
          <el-footer>
          </el-footer>
        </el-container>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Car',
  data () {
    return {
      // 用户默认头像url
      defaultHeadImageUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      tableData: [
        {
          id: '商品id',
          url: '商品图片url',
          name: '商品名称',
          number: 1,
          price: 1,
          stock: 1
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
      multipleSelection: [],
      totalPrice: 0,
      fit: 'scale-down'
    }
  },
  created () {
  },
  mounted () {
    this.handleCar()
    this.getUserInfo()
  },
  methods: {
    toggleSelection (rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
      this.countTotalPrice(val)
    },
    // 登出
    signOut () {
      // TODO 发送请求去除session
      this.$router.push('/login')
    },
    // 计算总价
    countTotalPrice (val) {
      this.multipleSelection = val
      this.totalPrice = 0
      for (let item of this.multipleSelection) {
        this.totalPrice += (item.price * item.number)
      }
    },
    // 从购物车中删除商品
    handleDelete (index, row) {
      const _this = this
      this.$confirm('确认删除此商品?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        this.$axios.delete('/lucky/car/item', {
          params: {
            id: row.id
          }
        }).then((res) => {
          if (res.data.code === 200) {
            this.$message.success('删除成功!')
            _this.handleCar()
          } else {
            this.$message.success('删除失败!')
          }
        })
      }).catch(() => {
        this.$message.info('已取消删除!')
      })
    },
    // 商品数量改变
    handleChange (number, id) {
      let updateCarItemAO = {
        'id': id,
        'number': number
      }
      this.$axios.post('/lucky/car/itemNumber', updateCarItemAO)
        .then((res) => {
        }).catch(() => {
          this.$message.error('请求出错!')
        })
      this.countTotalPrice(this.multipleSelection)
    },
    // 获取购物车信息
    handleCar () {
      const _this = this
      this.$axios.get('/lucky/car/item').then((res) => {
        console.log(res.data.data)
        if (res.data.code === 200) {
          _this.tableData = res.data.data
        }
      })
    },
    // 提交订单
    handleSubmitOrder () {
      this.$store.commit('setMultipleSelection', this.multipleSelection)
      if (this.multipleSelection.length > 0) {
        this.$router.push('payMent')
      } else {
        this.$message.error('请选择商品!')
      }
    },
    // 获取用户个人页信息
    getUserInfo () {
      const _this = this
      this.$axios.get('/lucky/user/info').then((res) => {
        _this.user = res.data.data
        console.log(res.data.data)
        if (_this.user.headImageUrl.length > 0) {
          _this.defaultHeadImageUrl = _this.user.headImageUrl
        }
      })
    }
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

  .el-menu-item, .el-submenu__title {
    height: 60px;
    line-height: 60px;
  }
</style>
