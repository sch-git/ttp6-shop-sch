<template>
  <div>
    <!--导航栏-->
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="4">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>属性管理</el-breadcrumb-item>
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
              <el-input
                clearable
                placeholder="请输入属性名"
                prefix-icon="el-icon-search"
                v-model="name">
              </el-input>
            </el-col>
            <el-col :offset='15' :span="1">
              <el-button @click="handleQuerySelect" type="primary">查询</el-button>
            </el-col>
            <el-col :offset='1' :span="2">
              <el-button @click="dialogFormVisible = true" type="primary">添加属性</el-button>
              <!--添加属性模态窗-->
              <el-dialog :visible.sync="dialogFormVisible" title="添加属性">
                <el-form :model="propertyForm">
                  <el-form-item :label-width="formLabelWidth" label="属性名称">
                    <el-input autocomplete="off" v-model="propertyForm.name"></el-input>
                  </el-form-item>
                </el-form>
                <div class="dialog-footer" slot="footer">
                  <el-button @click="dialogFormVisible = false,propertyForm.name=''">取 消</el-button>
                  <el-button @click="addProperty" type="primary">确 定</el-button>
                </div>
              </el-dialog>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--分类数据表格-->
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          :data="showTableData"
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
            label="属性ID"
            width="100">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="属性名"
            prop="name"
            width="120">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建人名称"
            prop="creatorName"
            show-overflow-tooltip
            width="130">
            <template slot-scope="scope">{{ scope.row.creatorName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建日期"
            prop="createTime"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.createTime }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="最后修改人名称"
            prop="lastModifierName"
            show-overflow-tooltip
            width="130">
            <template slot-scope="scope">{{ scope.row.lastModifierName }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="最后修改时间"
            prop="lastModifyTime"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.lastModifyTime }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="查看详情"
            show-overflow-tooltip
            width="130">
            <template slot-scope="scope">
              <el-button @click="handleSearchPropertyValue(scope.row)" plain size="small"
                         type="primary">
                查看详情
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作"
            show-overflow-tooltip
            width="130">
            <template slot-scope="scope">
              <el-button @click="handleSearch(scope.$index, scope.row)" size="small" type="primary">编辑</el-button>
              <el-dialog :visible.sync="updateForm" title="编辑属性">
                <el-form :model="propertyForm">
                  <el-form-item :label-width="formLabelWidth" label="属性名称">
                    <el-input autocomplete="off" v-model="propertyForm.name"></el-input>
                  </el-form-item>
                </el-form>
                <div class="dialog-footer" slot="footer">
                  <el-button @click="updateForm = false,propertyForm.name=''">取 消</el-button>
                  <el-button @click="updateProperty(propertyFormRow)" type="primary">确 定</el-button>
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
  created () {
  },
  mounted () {
    this.getAdminInfo()
    this.getPropertys()
  },
  data () {
    return {
      // 管理员信息
      adminInfo: {
        name: ''
      },
      // 搜索栏：属性名
      name: '',
      multipleSelection: [],
      // 分类总数据
      TableData: [
        {
          id: '',
          name: '王小虎',
          parentCategoryId: '',
          creatorName: '',
          createTime: '',
          lastModifierName: '',
          lastModifyTime: '',
          status: ''
        }
      ],
      // 当前页展示的分类数据
      showTableData: [
        {
          id: '',
          name: '王小虎',
          parentCategoryId: '',
          creatorName: '',
          createTime: '',
          lastModifierName: '',
          lastModifyTime: '',
          status: ''
        }
      ],
      // 添加模态窗显示
      dialogFormVisible: false,
      // 编辑模态窗显示
      updateForm: false,
      // 模态窗数据
      propertyForm: {
        name: ''
      },
      // 模态窗修改行数据
      propertyFormRow: '',
      formLabelWidth: '100px',
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
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
      this.showTableData = this.show
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      this.showTableData = this.show
    },
    // 编辑属性按钮
    handleSearch (index, row) {
      this.updateForm = true
      this.propertyForm.name = row.name
      this.propertyFormRow = row
    },
    // 获取管理员信息
    getAdminInfo () {
      const _this = this
      this.$axios.get('/lucky/admin').then((res) => {
        if (res.data.code === 200) {
          _this.adminInfo = res.data.data
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
        _this.$router.push('/adminLogin')
      })
    },
    // 新增属性
    addProperty () {
      const _this = this
      let propertyAO = {
        'name': this.propertyForm.name,
        'creatorName': this.adminInfo,
        'lastModifierName': this.adminInfo
      }
      this.$axios.post('/lucky/admin/property/property', propertyAO).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
          _this.getPropertys()
          this.dialogFormVisible = false
        } else if (res.data.code === 404) {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('新增属性失败!')
      })
    },
    // 获取所有属性信息(表格显示)
    getPropertys () {
      const _this = this
      this.$axios.get('/lucky/admin/property/propertyList').then((res) => {
        _this.TableData = res.data.data
        _this.showTableData = _this.show
        _this.totalPage = _this.TableData.length
      })
    },
    // 查询该属性的属性值
    handleSearchPropertyValue (row) {
      this.$router.push('goodPropertyValue/' + row.id)
    },
    // 模糊查询
    handleQuerySelect () {
      const _this = this
      let propertyAO = {
        'name': this.name
      }
      this.$axios.post('/lucky/admin/property/propertyList', propertyAO).then((res) => {
        if (res.data.code === 200) {
          _this.TableData = res.data.data
          _this.totalPage = _this.TableData.length
          _this.showTableData = _this.show
          _this.$message.success(res.data.msg)
        } else if (res.data.code === 404) {
          _this.TableData = res.data.data
          _this.showTableData = []
          _this.totalPage = 0
          _this.$message.error(res.data.msg)
        }
      })
    },
    // 编辑模态窗确认
    updateProperty (row) {
      const _this = this
      this.$confirm('此操作将修改属性，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let propertyAO = {
          'id': row.id,
          'name': this.propertyForm.name,
          'lastModifierName': this.adminInfo
        }
        this.$axios.put('/lucky/admin/property/property', propertyAO).then((res) => {
          if (res.data.code === 200) {
            _this.$message.success(res.data.msg)
            _this.propertyFormRow.name = propertyAO.name
            _this.propertyFormRow.lastModifierName = propertyAO.lastModifierName
            _this.propertyFormRow.lastModifyTime = res.data.data.lastModifyTime
            this.propertyForm.name = ''
            _this.updateForm = false
          } else if (res.data.code === 404) {
            _this.$message.error(res.data.msg)
          }
        })
      }).catch(() => {
        _this.$message.info('已取消修改!')
      })
    }
  },
  computed: {
    // 显示当前页的分类数据
    show () {
      return this.TableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  }
}
</script>

<style scoped>

</style>
