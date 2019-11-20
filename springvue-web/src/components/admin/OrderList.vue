<template>
  <div>
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>订单列表</el-breadcrumb-item>
              </el-breadcrumb>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--搜索栏-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="5">
              <el-select clearable placeholder="请选择订单状态" v-model="statusValue" value="">
                <el-option
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  v-for="item in status">
                </el-option>
              </el-select>
            </el-col>
            <el-col :offset='15' :span="2">
              <el-button @click="handleSearch" type="primary">查询</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--数据表格-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          :data="showTableData"
          border
          style="width: 100%"
          tooltip-effect="dark">
          <el-table-column
            align="center"
            label="订单id"
            prop="id"
            width="100">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="订单编号"
            prop="orderNo">
            <template slot-scope="scope">{{ scope.row.orderNo }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建日期"
            prop="createDate"
            width="200"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.createDate }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="收货人"
            prop="receiver"
            show-overflow-tooltip
          width="150">
            <template slot-scope="scope">{{ scope.row.receiver }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="总价"
            prop="totalPrice"
            width="150"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.totalPrice }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="状态"
            prop="status"
            width="120"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button :type="scope.row.status===0?'danger':scope.row.status===1?'primary':'success'"
                         plain size="small"
              v-text="scope.row.status===0?'已取消':scope.row.status===1?'待支付':'已完成'"></el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作"
            show-overflow-tooltip
            width="150">
            <template slot-scope="scope">
              <el-button @click="handleSearchInfo(scope.$index, scope.row)" size="small" type="primary">查看详情</el-button>
              <!--模态窗-->
              <el-dialog :visible.sync="dialogFormVisible" title="订单详情" width="60%">
                <!--表格-->
                <el-main>
                  <el-table
                    :data="showTableDataInfo"
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
                <div class="dialog-footer" slot="footer">
                  <el-button @click="dialogFormVisible = false">取 消</el-button>
                  <el-button @click="dialogFormVisible = false" type="primary">确 定</el-button>
                </div>
              </el-dialog>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <!--分页-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :offset="10" :span="12">
              <div class="block">
                <el-pagination
                  :current-page=currentPage
                  :page-size=pageSize
                  :page-sizes="[1, 5, 10, 15, 20, 30]"
                  :total=totalPage
                  @current-change="handleCurrentChange"
                  @size-change="handleSizeChange"
                  background
                  layout="total, sizes, prev, pager, next, jumper">
                </el-pagination>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      orderId: '',
      statusValue: '',
      status: [
        {
          value: 2,
          label: '已完成'
        },
        {
          value: 1,
          label: '待支付'
        },
        {
          value: 0,
          label: '已取消'
        }
      ],
      // 表格总数据
      tableData: [
        {
          id: 0,
          orderNo: '1',
          address: '上海市普陀区金沙江路',
          createDate: '2019-09',
          status: '正常',
          receiver: '收货人',
          phone: '',
          totalPrice: '10.00'
        }
      ],
      // 展示表格总数据
      showTableData: [
        {
          id: 0,
          orderNo: '1',
          address: '上海市普陀区金沙江路',
          createDate: '2019-09',
          status: '正常',
          receiver: '收货人',
          phone: '',
          totalPrice: '10.00'
        }
      ],
      // 订单详情数据表
      showTableDataInfo: [],
      dialogFormVisible: false,
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1
    }
  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.showTableData = this.showTable()
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.showTableData = this.showTable()
    },
    // 查询订单
    handleSearch () {
      const _this = this
      let param = new URLSearchParams()
      param.append('status', this.statusValue)
      this.$axios.post('/lucky/admin/order/order', param).then((res) => {
        if (res.data.code === 200) {
          _this.tableData = res.data.data
          _this.totalPage = _this.tableData.length
          _this.showTableData = _this.showTable()
        } else {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    },
    // 查看详情
    handleSearchInfo (index, row) {
      console.log(index, row)
      this.dialogFormVisible = true
      this.getOrderItemList(row.id)
    },
    // 获取订单信息
    getOrderList () {
      const _this = this
      this.$axios.get('/lucky/admin/order/order').then((res) => {
        if (res.data.code === 200) {
          _this.tableData = res.data.data
          _this.totalPage = _this.tableData.length
          _this.showTableData = _this.showTable()
        } else {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    },
    // 获取订单详情数据
    getOrderItemList (orderId) {
      const _this = this
      this.$axios.get('/lucky/admin/order/orderItem', {
        params: {
          'orderId': orderId
        }
      }).then((res) => {
        if (res.data.code === 200) {
          _this.showTableDataInfo = res.data.data
        } else {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
    },
    // 展示数据
    showTable () {
      return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  created () {
  },
  mounted () {
    this.getOrderList()
  }
}
</script>

<style scoped>

</style>
