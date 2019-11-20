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
                <el-breadcrumb-item>分类管理</el-breadcrumb-item>
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
                placeholder="请输入分类名"
                prefix-icon="el-icon-search"
                v-model="categoryName">
              </el-input>
            </el-col>
            <el-col :span="5">
              <el-select clearable placeholder="请选择分类状态" v-model="categoryStatus" value="">
                <el-option
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  v-for="item in status">
                </el-option>
              </el-select>
            </el-col>
            <el-col :offset='10' :span="1">
              <el-button @click="handleQueryCategorySelect" type="primary">查询</el-button>
            </el-col>
            <el-col :offset='1' :span="2">
              <el-button @click="dialogFormVisible = true" type="primary">添加分类</el-button>
              <!--添加分类模态窗-->
              <el-dialog :visible.sync="dialogFormVisible" title="添加分类">
                <el-form :model="categoryForm">
                  <el-form-item :label-width="formLabelWidth" label="分类名称">
                    <el-input autocomplete="off" v-model="categoryForm.name"></el-input>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth" label="父分类">
                    <el-select placeholder="请选择父分类" v-model="categoryForm.parentCategory" value=0>
                      <el-option
                        :key="item.id"
                        :label="item.label"
                        :value="item.value"
                        v-for="item in parentCategorys">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div class="dialog-footer" slot="footer">
                  <el-button @click="dialogFormVisible = false">取 消</el-button>
                  <el-button @click="addCategory" type="primary">确 定</el-button>
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
          :data="showCategoryTableData"
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
            label="分类ID"
            width="100">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="分类名"
            prop="name"
            width="120">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="父分类id"
            prop="parentCategoryId"
            width="120">
            <template slot-scope="scope">{{ scope.row.parentCategoryId }}</template>
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
            label="状态"
            prop="status"
            show-overflow-tooltip
            width="130">
            <template slot-scope="scope">
              <el-button :type="scope.row.status===true?'primary':'danger'"
                         @click="handleChangeCategoryStatus(scope.row)" plain
                         size="small">
                {{ scope.row.status === true ? '正常' : '无效' }}
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
              <el-dialog :visible.sync="updateCategoryForm" title="编辑分类">
                <el-form :model="categoryForm">
                  <el-form-item :label-width="formLabelWidth" label="分类名称">
                    <el-input autocomplete="off" v-model="categoryForm.name"></el-input>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth" label="父分类">
                    <el-select placeholder="请选择父分类" v-model="categoryForm.parentCategory" value=0>
                      <el-option
                        :disabled="item.id===categoryForm.disableId"
                        :key="item.id"
                        :label="item.label"
                        :value="item.value"
                        v-for="item in parentCategorys">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth" label="分类状态">
                    <el-select placeholder="请选择分类状态" v-model="categoryForm.status" value="">
                      <el-option
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                        v-for="item in status">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div class="dialog-footer" slot="footer">
                  <el-button @click="updateCategoryForm = false,categoryForm.name=''">取 消</el-button>
                  <el-button @click="updateCategory(FormRow)" type="primary">确 定</el-button>
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
    this.getParentCategorys()
  },
  mounted () {
    this.getAdminInfo()
    this.getCategorys()
  },
  data () {
    return {
      // 管理员信息
      adminInfo: {
        name: ''
      },
      // 搜索栏：分类id
      categoryName: '',
      // 搜索栏：分类状态
      categoryStatus: '',
      categoryValue: '',
      multipleSelection: [],
      status: [
        {
          value: true,
          label: '正常'
        },
        {
          value: false,
          label: '无效'
        }
      ],
      // 分类总数据
      categoryTableData: [
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
      showCategoryTableData: [
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
      // 添加分类模态窗显示
      dialogFormVisible: false,
      // 编辑分类模态窗显示
      updateCategoryForm: false,
      // 模态窗口数据
      categoryForm: {
        name: '',
        parentCategory: '0',
        status: true,
        disableId: ''
      },
      // 模态窗修改行数据
      FormRow: '',
      // 所有父分类
      parentCategorys: [
        {
          label: '无',
          value: 0,
          id: 0
        }
      ],
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
      this.showCategoryTableData = this.showCategory()
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      this.showCategoryTableData = this.showCategory()
    },
    // 编辑分类按钮
    handleSearch (index, row) {
      this.updateCategoryForm = true
      this.categoryForm.name = row.name
      this.categoryForm.disableId = row.id
      this.categoryForm.status = row.status
      // 获取当前点击分类的父分类
      this.getParentCategory(row.id)
      this.FormRow = row
    },
    // 获取分类的父分类
    getParentCategory (id) {
      const _this = this
      this.$axios.get('/lucky/admin/category/parent', { params: { 'id': id } }).then((res) => {
        if (res.data.code === 200) {
          _this.categoryForm.parentCategory = res.data.data.parentCategoryId
        } else {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('请求出错!')
      })
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
    // 获取所有父分类信息(状态正常)
    getParentCategorys () {
      const _this = this
      this.$axios.get('/lucky/admin/category/names').then((res) => {
        let categoryVOs = res.data.data
        categoryVOs.map((categoryVO) => {
          _this.parentCategorys.push(
            {
              label: categoryVO.name,
              value: categoryVO.id,
              id: categoryVO.id
            }
          )
        })
      })
    },
    // 新增分类
    addCategory () {
      const _this = this
      let categoryAO = {
        'name': this.categoryForm.name,
        'parentCategoryId': this.categoryForm.parentCategory,
        'creatorName': this.adminInfo,
        'lastModifierName': this.adminInfo
      }
      this.$axios.post('/lucky/admin/category/category', categoryAO).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
          this.categoryForm.name = ''
          this.categoryForm.parentCategory = 0
          _this.getCategorys()
        } else if (res.data.code === 404) {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('新增分类失败!')
      })
      this.dialogFormVisible = false
    },
    // 获取所有分类信息(表格显示)
    getCategorys () {
      const _this = this
      this.$axios.get('/lucky/admin/category/categoryAll').then((res) => {
        _this.categoryTableData = res.data.data
        _this.showCategoryTableData = _this.showCategory()
        _this.totalPage = _this.categoryTableData.length
      })
    },
    // 修改分类状态
    handleChangeCategoryStatus (row) {
      const _this = this
      this.$confirm('此操作将修改分类状态，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        let categoryAO = {
          'id': row.id,
          'status': !row.status,
          'lastModifierName': this.adminInfo
        }
        this.$axios.post('/lucky/admin/category/status', categoryAO).then((res) => {
          console.log(res.data)
          if (res.data.code === 200) {
            _this.$message.success(res.data.msg)
            row.status = !row.status
          } else if (res.data.code === 404) {
            _this.$message.error(res.data.msg)
          }
        })
      }).catch(() => {
        _this.$message.info('已取消修改!')
      })
    },
    // 模糊查询分类
    handleQueryCategorySelect () {
      const _this = this
      let categoryAO = {
        'name': this.categoryName,
        'status': this.categoryStatus
      }
      this.$axios.post('/lucky/admin/category/list', categoryAO).then((res) => {
        if (res.data.code === 200) {
          _this.categoryTableData = res.data.data
          _this.totalPage = _this.categoryTableData.length
          _this.showCategoryTableData = _this.showCategory()
          _this.$message.success(res.data.msg)
        } else if (res.data.code === 404) {
          _this.categoryTableData = res.data.data
          _this.showCategoryTableData = []
          _this.totalPage = 0
          _this.$message.error(res.data.msg)
        }
      })
    },
    // 编辑分类模态窗确认
    updateCategory (row) {
      const _this = this
      this.$confirm('此操作将修改分类，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let categoryAO = {
          'id': row.id,
          'name': this.categoryForm.name,
          'parentCategoryId': this.categoryForm.parentCategory,
          'status': this.categoryForm.status,
          'lastModifierName': this.adminInfo
        }
        this.$axios.put('/lucky/admin/category/category', categoryAO).then((res) => {
          if (res.data.code === 200) {
            _this.$message.success(res.data.msg)
            row.name = categoryAO.name
            row.parentCategoryId = categoryAO.parentCategoryId
            row.status = categoryAO.status
          } else if (res.data.code === 404) {
            _this.$message.error(res.data.msg)
          }
        })
        this.categoryForm.name = ''
        _this.updateCategoryForm = false
      }).catch(() => {
        _this.$message.info('已取消修改!')
      })
    },
    // 显示当前页的分类数据
    showCategory () {
      return this.categoryTableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  }
}
</script>

<style scoped>

</style>
