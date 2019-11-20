<template>
  <div>
    <!--导航栏-->
    <el-row :gutter="12">
      <el-col :span="24">
        <el-card shadow="always">
          <el-row :gutter="12">
            <el-col :span="6">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/admin/goodProperty' }">属性管理</el-breadcrumb-item>
                <el-breadcrumb-item>属性值管理</el-breadcrumb-item>
              </el-breadcrumb>
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
            label="属性值ID"
            width="100">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="属性值"
            prop="value"
            width="120">
            <template slot-scope="scope">{{ scope.row.value }}</template>
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
            label="操作"
            show-overflow-tooltip
            width="200">
            <template slot-scope="scope">
              <el-button @click="handleSearch(scope.$index, scope.row)" size="small" type="primary">编辑</el-button>
              <el-dialog :visible.sync="updateForm" title="编辑属性值">
                <el-form :model="propertyValueForm">
                  <el-form-item :label-width="formLabelWidth" label="属性值">
                    <el-input autocomplete="off" v-model="propertyValueForm.name"></el-input>
                  </el-form-item>
                </el-form>
                <div class="dialog-footer" slot="footer">
                  <el-button @click="updateForm = false,propertyValueForm.name=''">取 消</el-button>
                  <el-button @click="updateProperty(propertyValueFormRow)" type="primary">确 定</el-button>
                </div>
              </el-dialog>
              <el-button @click="handleDelete(scope.$index, scope.row)" size="small" type="danger">删除</el-button>
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
    this.propertyId = this.$route.params.id
  },
  mounted () {
    this.getAdminInfo()
    this.getPropertyValues()
  },
  data () {
    return {
      // 管理员信息
      adminInfo: {
        name: ''
      },
      // 属性id
      propertyId: '',
      multipleSelection: [],
      // 分类总数据
      TableData: [
        {
          id: '',
          name: '王小虎',
          creatorName: '',
          createTime: '',
          lastModifierName: '',
          lastModifyTime: ''
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
      // 编辑模态窗显示
      updateForm: false,
      // 模态窗数据
      propertyValueForm: {
        name: ''
      },
      // 模态窗修改行数据
      propertyValueFormRow: '',
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
    // 编辑按钮
    handleSearch (index, row) {
      this.updateForm = true
      this.propertyValueForm.name = row.value
      this.propertyValueFormRow = row
    },
    // 删除按钮
    handleDelete (index, row) {
      this.$message.error('禁止删除!')
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
    // 根据属性id，获取所有属性信息(表格显示)
    getPropertyValues () {
      const _this = this
      this.$axios.get('/lucky/admin/property/valueList', {
        params: {
          propertyId: this.propertyId
        }
      }).then((res) => {
        _this.TableData = res.data.data
        _this.showTableData = _this.show
        _this.totalPage = _this.TableData.length
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
        let propertyValueAO = {
          'id': row.id,
          'value': this.propertyValueForm.name,
          'lastModifierName': this.adminInfo
        }
        this.$axios.put('/lucky/admin/property/value', propertyValueAO).then((res) => {
          if (res.data.code === 200) {
            _this.$message.success(res.data.msg)
            _this.propertyValueFormRow.name = propertyValueAO.value
            _this.propertyValueFormRow.lastModifierName = propertyValueAO.lastModifierName
            _this.propertyValueForm.name = ''
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
