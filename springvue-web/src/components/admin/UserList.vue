<template>
  <div>
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>用户列表</el-breadcrumb-item>
              </el-breadcrumb>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--搜索框-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="5">
              <el-input
                clearable
                placeholder="请输入用户名"
                prefix-icon="el-icon-search"
                v-model="name">
              </el-input>
            </el-col>
            <el-col :span="5">
              <el-select placeholder="请选择用户状态" v-model="value" value="">
                <el-option
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  v-for="item in status">
                </el-option>
              </el-select>
            </el-col>
            <el-col :offset='10' :span="2">
              <el-button @click="handleQueryUserSelect()" type="primary">查询</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--数据表格-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          :data="tableData"
          @selection-change="handleSelectionChange"
          border
          ref="multipleTable"
          style="width: 100%"
          tooltip-effect="dark">
          <el-table-column
            align="center"
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            align="center"
            label="ID"
            width="60">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="用户名"
            prop="name"
            width="150">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="电话"
            prop="phone"
            show-overflow-tooltip
            width="150">
            <template slot-scope="scope">{{ scope.row.phone }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="邮箱"
            prop="email"
            width="200"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.email }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="地址"
            prop="address"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.address }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建日期"
            prop="createDate"
            show-overflow-tooltip
            width="200">
            <template slot-scope="scope">{{ scope.row.createDate }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="积分"
            prop="totalScore"
            width="100"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button :disabled="scope.row.totalScore===0?true:false" @click="handleDialogScore(scope.row.id)" type="text">
                {{ scope.row.totalScore }}
              </el-button>
              <el-dialog
                :visible.sync="dialogScore"
                center
                title="积分历史记录"
              width="30%">
                <el-timeline>
                  <el-timeline-item
                    :key="score.id"
                    :timestamp="score.createTime"
                    v-for="score in scoreList">
                    <span style="color: #53D51B">积分增加+{{score.score}}</span>
                  </el-timeline-item>
                </el-timeline>
                <span class="dialog-footer" slot="footer">
                  <el-button @click="dialogScore = false">取 消</el-button>
                  <el-button @click="dialogScore = false" type="primary">确 定</el-button>
                </span>
              </el-dialog>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="状态"
            prop="status"
            width="100"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button :type="scope.row.status===true?'primary':'danger'" @click="handleChangeUserStatus(scope.row)"
                         plain
                         size="small">
                {{ scope.row.status === true ? '正常' : '已禁用' }}
              </el-button>
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
                  :page-sizes="[1, 10, 15, 20, 30]"
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
  name: 'UserList',
  mounted () {
    this.handleQueryUserList()
  },
  data () {
    return {
      // 查询用户名
      name: '',
      multipleSelection: [],
      // 查询用户状态
      value: '',
      status: [
        {
          value: 'true',
          label: '正常'
        },
        {
          value: 'false',
          label: '已禁用'
        }
      ],
      // 积分模态窗
      dialogScore: false,
      scoreList: [
        {
          id: '',
          createTime: '',
          score: ''
        }
      ],
      // 表格数据
      tableData: [],
      users: [],
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1
    }
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
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.tableData = this.showUser
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
      this.currentPage = val
      this.tableData = this.showUser
    },
    // 查询所有用户
    handleQueryUserList () {
      const _this = this
      this.$axios.get('/lucky/admin/userList').then((res) => {
        _this.users = res.data.data
        _this.tableData = this.users.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
        _this.totalPage = _this.users.length
        console.log(_this.tableData)
      })
    },
    // 修改用户状态
    handleChangeUserStatus (row) {
      this.$confirm('此操作将修改用户状态，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(() => {
        let userAO = {
          'id': row.id,
          'status': !row.status
        }
        this.$axios.post('/lucky/admin/userStatus', userAO).then((res) => {
          if (res.data.code === 200) {
            this.$message.success(res.data.msg)
          } else if (res.data.code === 404) {
            this.$message.error(res.data.msg)
          }
          row.status = !row.status
        })
      }).catch(() => {
        this.$message.info('已取消修改!')
      })
    },
    // 模糊查询用户
    handleQueryUserSelect () {
      const _this = this
      let userAO = {
        'name': this.name,
        'status': this.value
      }
      this.$axios.post('/lucky/admin/userList', userAO).then((res) => {
        if (res.data.code === 200) {
          _this.users = res.data.data
          _this.totalPage = _this.users.length
          _this.tableData = _this.users.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
          _this.$message.success(res.data.msg)
        } else if (res.data.code === 404) {
          _this.users = res.data.data
          _this.tableData = []
          _this.totalPage = 0
          _this.$message.error(res.data.msg)
        }
      })
    },
    // 查询用户积分历史
    handleDialogScore (userId) {
      this.dialogScore = true
      const _this = this
      let params = new URLSearchParams()
      params.append('id', userId)
      this.$axios.post('/lucky/admin/point', params).then((res) => {
        if (res.data.code === 200) {
          _this.scoreList = res.data.data
        } else {
          this.$message.error('请求失败!')
        }
      }).catch(() => {
        this.$message.error('请求出错!')
      })
    }
  },
  computed: {
    showUser () {
      return this.users.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  }
}
</script>

<style scoped>

</style>
