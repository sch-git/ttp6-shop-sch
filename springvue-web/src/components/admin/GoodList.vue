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
                <el-breadcrumb-item>商品列表</el-breadcrumb-item>
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
                placeholder="请输入商品名"
                prefix-icon="el-icon-search"
                v-model="name">
              </el-input>
            </el-col>
            <el-col :span="5">
              <el-select clearable placeholder="请选择" v-model="categoryValue">
                <el-option
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                  v-for="item in categorys">
                </el-option>
              </el-select>
            </el-col>
            <el-col :offset='10' :span="1">
              <el-button @click="handleSearch" type="primary">查询</el-button>
            </el-col>
            <!--添加商品模态窗-->
            <el-col :offset='1' :span="2">
              <el-button @click="dialogFormVisible = true" type="primary">添加商品</el-button>
              <el-dialog :visible.sync="dialogFormVisible" title="添加商品">
                <el-form :model="addGoodForm">
                  <el-form-item :label-width="formLabelWidth" label="名称">
                    <el-input autocomplete="off" v-model="addGoodForm.name"></el-input>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth" label="库存">
                    <el-input autocomplete="off" v-model="addGoodForm.stock"></el-input>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth" label="价格">
                    <el-input autocomplete="off" v-model="addGoodForm.price"></el-input>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth" label="描述">
                    <el-input :rows="2" autocomplete="off" placeholder="请输入商品描述"
                              type="textarea" v-model="addGoodForm.description"></el-input>
                  </el-form-item>
                </el-form>
                <div class="dialog-footer" slot="footer">
                  <el-button @click="dialogFormVisible = false">取 消</el-button>
                  <el-button @click="addGood" type="primary">确 定</el-button>
                </div>
              </el-dialog>
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
            label="商品ID"
            width="70">
            <template slot-scope="scope">{{ scope.row.id }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="商品名"
            prop="name"
            width="130">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="库存"
            prop="stock"
            show-overflow-tooltip
            width="80">
            <template slot-scope="scope">{{ scope.row.stock }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="价格"
            prop="price"
            width="80"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.price }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            label="创建人名称"
            prop="creatorName"
            show-overflow-tooltip
            width="120">
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
            width="120">
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
            width="120"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button :type="scope.row.status===true?'primary':'danger'" @click="handleChangeGoodStatus(scope.row)"
                         plain
                         size="small">
                {{ scope.row.status === true ? '正常' : '已下架' }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作"
            width="120"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <!--编辑模态窗-->
              <el-button @click="handleUpdate(scope.$index, scope.row)"
                         size="small" type="primary">编辑
              </el-button>
              <el-dialog
                :visible.sync="updateDialogVisible"
                title="提示"
                width="30%">
                <!--修改商品基本信息模态窗-->
                <el-button @click="goodInfo(updateGoodRow)" plain type="primary">基本信息</el-button>
                <el-dialog :visible.sync="updateGoodFormVisible" append-to-body title="修改商品信息">
                  <el-form :model="addGoodForm">
                    <el-form-item :label-width="formLabelWidth" label="名称">
                      <el-input autocomplete="off" v-model="addGoodForm.name"></el-input>
                    </el-form-item>
                    <el-form-item :label-width="formLabelWidth" label="库存">
                      <el-input autocomplete="off" v-model="addGoodForm.stock"></el-input>
                    </el-form-item>
                    <el-form-item :label-width="formLabelWidth" label="价格">
                      <el-input autocomplete="off" v-model="addGoodForm.price"></el-input>
                    </el-form-item>
                    <el-form-item :label-width="formLabelWidth" label="描述">
                      <el-input :rows="2" autocomplete="off" placeholder="请输入商品描述"
                                type="textarea" v-model="addGoodForm.description"></el-input>
                    </el-form-item>
                    <el-form-item :label-width="formLabelWidth" label="商品状态">
                      <el-select placeholder="请选择分类状态" v-model="addGoodForm.status" value="">
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
                    <el-button @click="updateGoodFormVisible = false">取 消</el-button>
                    <el-button @click="updateGoodInfo(updateGoodRow)" type="primary">确 定</el-button>
                  </div>
                </el-dialog>
                <!--修改商品图片模态窗-->
                <el-button @click="imageInfo(updateGoodRow)" plain style="margin-left: 10px" type="primary">商品图片
                </el-button>
                <el-dialog :visible.sync="addGoodImageVisible" append-to-body title="修改商品图片">
                  <el-row>
                    <el-col :key="url" :span="8" v-for="url in imageUrlList">
                      <img :src="url"
                           class="avatar" style="margin-bottom: 15px">
                    </el-col>
                    <el-col :span="8">
                    <el-upload
                      :before-upload="beforeAvatarUpload"
                      :on-success="handleAvatarSuccess"
                      :show-file-list="false"
                      action="http://localhost:8088/lucky/upload/image"
                      class="el-upload--picture-card"
                      name="picture">
                      <i class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                    </el-col>
                  </el-row>
                  <div class="dialog-footer" slot="footer">
                    <el-button @click="addGoodImageCancel">取 消</el-button>
                    <el-button @click="addGoodImage(updateGoodRow)" type="primary">确 定</el-button>
                  </div>
                </el-dialog>
                <!--修改商品分类信息模态窗-->
                <el-button @click="categoryInfo(updateGoodRow)" plain style="margin-left: 10px" type="primary">商品分类
                </el-button>
                <el-dialog :visible.sync="categoryInfoFormVisible" append-to-body title="修改商品分类">
                  <el-checkbox-group v-model="selectCategorys">
                    <el-checkbox
                      :key="item.id"
                      :label="item.id+'.'+item.label"
                      border
                      v-for="item in categorysInfo"
                      style="margin-left: 0;margin-bottom: 15px"
                    ></el-checkbox>
                  </el-checkbox-group>
                  <div class="dialog-footer" slot="footer">
                    <el-button @click="categoryInfoFormVisible = false,selectCategorys=[]">取 消</el-button>
                    <el-button @click="updateGoodCategory(updateGoodRow)" type="primary">确 定</el-button>
                  </div>
                </el-dialog>
                <!--修改商品属性信息模态窗-->
                <el-button @click="propertyInfo(updateGoodRow)" plain style="margin-left: 10px" type="primary">添加属性
                </el-button>
                <el-dialog :visible.sync="addPropertyInfoFormVisible" append-to-body title="修改商品属性">
                  <el-form class="demo-dynamic" label-width="100px">
                    <el-form-item>
                      <el-select placeholder="请选择商品属性" v-model="property" value="">
                        <el-option
                          :key="item.id"
                          :label="item.name"
                          :value="item.id"
                          v-for="item in propertys">
                        </el-option>
                      </el-select>
                      <el-input style="margin-top: 10px" v-model="propertyValue"></el-input>
                    </el-form-item>
                  </el-form>
                  <div class="dialog-footer" slot="footer">
                    <el-button @click="addPropertyInfoFormVisible = false">取 消</el-button>
                    <el-button @click="addGoodProperty(updateGoodRow)" type="primary">确 定</el-button>
                  </div>
                </el-dialog>
                <span class="dialog-footer" slot="footer">
                  <el-button @click="updateDialogVisible = false">取 消</el-button>
                  <el-button @click="updateDialogVisible = false" type="primary">确 定</el-button>
                </span>
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
    this.getCategorys()
    this.getGoods()
  },
  mounted () {
    this.getAdminInfo()
  },
  data () {
    return {
      // 管理员信息
      adminInfo: {
        name: ''
      },
      // 图片上传
      imageUrl: '',
      imageUrlList: [],
      addUrlList: [],
      // 搜索栏
      name: '',
      statusValue: '',
      categoryValue: '',
      status: [
        {
          value: true,
          label: '正常'
        },
        {
          value: false,
          label: '下架'
        }
      ],
      categorys: [
        {
          id: '分类id',
          name: '分类名'
        }
      ],
      // 添加属性窗口数据
      property: '',
      propertys: [],
      propertyValue: '',
      // 商品所属分类数据
      categorysInfo: [],
      // 商品已选分类
      selectCategorys: [],
      selectCategorysId: [],
      selectCategorysName: [],
      multipleSelection: [],
      // 编辑按钮当前行数据
      updateGoodRow: '',
      // 分类总数据
      tableData: [
        {
          id: '',
          name: '王小虎',
          stock: '库存',
          price: '10.00',
          status: '正常',
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
          stock: '库存',
          price: '10.00',
          status: '正常',
          creatorName: '',
          createTime: '',
          lastModifierName: '',
          lastModifyTime: ''
        }
      ],
      // 编辑外层模态窗显示
      updateDialogVisible: false,
      // 添加，修改商品模态窗显示
      dialogFormVisible: false,
      updateGoodFormVisible: false,
      // 添加,修改商品模态窗数据
      addGoodForm: {
        name: '',
        stock: '',
        price: '',
        description: '',
        // 修改商品所需数据
        status: true
      },
      // 修改商品分类窗口显示
      categoryInfoFormVisible: false,
      // 添加商品属性窗口显示
      addPropertyInfoFormVisible: false,
      // 添加图片窗口显示
      addGoodImageVisible: false,
      // 每页几条数据
      pageSize: 5,
      // 总页数
      totalPage: 0,
      // 当前页
      currentPage: 1,
      formLabelWidth: '100px'
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
    // 搜索栏 分类
    handleChange (value) {

    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    // 每页几条数据变化
    handleSizeChange (val) {
      this.pageSize = val
      this.showTableData = this.showGood()
    },
    // 当前页变化
    handleCurrentChange (val) {
      this.currentPage = val
      this.showTableData = this.showGood()
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
    // 查询按钮
    handleSearch () {
      const _this = this
      let goodCategoryAO = {
        'goodName': this.name,
        'categoryId': this.categoryValue
      }
      this.$axios.post('/lucky/admin/good/list', goodCategoryAO).then((res) => {
        if (res.data.code === 200) {
          _this.TableData = res.data.data
          this.$message.success(res.data.msg)
          if (_this.TableData.length > 0) {
            _this.showTableData = _this.showGood()
            _this.totalPage = _this.TableData.length
          } else {
            this.$message.error(res.data.msg)
          }
        }
      }).catch(() => {
        this.$message.error('查询失败!')
      })
    },
    // 修改商品状态
    handleChangeGoodStatus (row) {
      const _this = this
      this.$confirm('此操作将修改商品状态，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        let goodAO = {
          'id': row.id,
          'status': !row.status,
          'lastModifierName': this.adminInfo
        }
        this.$axios.put('/lucky/admin/good/status', goodAO).then((res) => {
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
    // 编辑按钮
    handleUpdate (index, row) {
      this.updateGoodRow = row
      this.updateGoodRow = row
      this.updateDialogVisible = true
    },
    // 新增商品 TODO
    addGood () {
      const _this = this
      let goodAO = {
        'name': this.addGoodForm.name,
        'stock': this.addGoodForm.stock,
        'price': this.addGoodForm.price,
        'description': this.addGoodForm.description,
        'creatorName': this.adminInfo,
        'lastModifierName': this.adminInfo
      }
      this.$axios.post('/lucky/admin/good/good', goodAO).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
          _this.getGoods()
          this.dialogFormVisible = false
        } else if (res.data.code === 404) {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('新增商品失败!')
      })
    },
    // 编辑->基本信息按钮->确认 修改商品数据 TODO
    updateGoodInfo (row) {
      const _this = this
      this.$confirm('此操作将修改分类，是否继续?', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let goodAO = {
          'id': row.id,
          'name': this.addGoodForm.name,
          'stock': this.addGoodForm.stock,
          'price': this.addGoodForm.price,
          'description': this.addGoodForm.description,
          'status': this.addGoodForm.status,
          'lastModifierName': this.adminInfo
        }
        this.$axios.put('/lucky/admin/good/good', goodAO).then((res) => {
          if (res.data.code === 200) {
            _this.$message.success(res.data.msg)
            row.name = goodAO.name
            row.price = goodAO.price
            row.stock = goodAO.stock
            row.status = goodAO.status
            row.description = goodAO.description
            _this.updateGoodFormVisible = false
          } else if (res.data.code === 404) {
            _this.$message.error(res.data.msg)
          }
        })
        this.addGoodForm.name = ''
        this.addGoodForm.stock = ''
        this.addGoodForm.price = ''
        this.addGoodForm.description = ''
      }).catch(() => {
        _this.$message.info('已取消修改!')
      })
    },
    // 编辑->基本信息按钮
    goodInfo (row) {
      this.updateGoodFormVisible = true
      this.addGoodForm.name = row.name
      this.addGoodForm.stock = row.stock
      this.addGoodForm.price = row.price
      this.addGoodForm.description = row.description
      this.addGoodForm.status = row.status
    },
    // 编辑->商品分类按钮
    categoryInfo (row) {
      this.categoryInfoFormVisible = true
      this.getGoodCategorys(row.id)
    },
    // 编辑->添加属性按钮
    propertyInfo (row) {
      this.property = ''
      this.addPropertyInfoFormVisible = true
      this.getPropertys(row)
    },
    // 编辑->添加属性值按钮->确认 修改商品属性值
    addGoodProperty (row) {
      const _this = this
      let propertyValueAO = {
        'propertyId': this.property,
        'goodId': row.id,
        'value': this.propertyValue,
        'creatorName': this.adminInfo,
        'lastModifierName': this.adminInfo
      }
      this.$axios.post('/lucky/admin/property/value', propertyValueAO).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
          _this.getGoods()
          this.addPropertyInfoFormVisible = false
        } else if (res.data.code === 404) {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('新增属性失败!')
      })
    },
    // 获取所有分类
    getCategorys () {
      const _this = this
      this.$axios.get('/lucky/admin/category/categoryAll').then((res) => {
        _this.categorys = res.data.data
        _this.categorys.map((item) => {
          _this.categorysInfo.push({ 'id': item.id, 'label': item.name, 'disable': item.status === false })
        })
      })
    },
    // 获取商品没有的属性
    getPropertys (row) {
      const _this = this
      this.$axios.get('/lucky/admin/property/list', {
        params: {
          'goodId': row.id
        }
      }).then((res) => {
        _this.propertys = res.data.data
      })
    },
    // 获取已选商品分类
    getGoodCategorys (id) {
      this.$axios.get('/lucky/admin/category/list', {
        params: {
          'id': id
        }
      }).then((res) => {
        res.data.data.map((item) => {
          this.selectCategorys.push(item.categoryId + '.' + item.categoryName)
        })
      })
    },
    // 编辑->商品分类按钮->确认 修改商品分类
    updateGoodCategory (row) {
      const _this = this
      let goodCategoryAOList = []
      this.selectCategorys.map((item) => {
        goodCategoryAOList.push({
          'goodId': row.id,
          'goodName': row.name,
          'categoryId': item.split('.')[0],
          'categoryName': item.split('.')[1],
          'creatorName': this.adminInfo,
          'lastModifierName': this.adminInfo
        })
      })
      this.$axios.post('/lucky/admin/category/goodCategory', goodCategoryAOList).then((res) => {
        if (res.data.code === 200) {
          _this.$message.success(res.data.msg)
          this.categoryInfoFormVisible = false
          this.selectCategorys = []
        } else if (res.data.code === 404) {
          _this.$message.error(res.data.msg)
        }
      }).catch(() => {
        _this.$message.error('新增商品失败!')
      })
    },
    // 获取所有商品信息(表格显示)
    getGoods () {
      const _this = this
      this.$axios.get('/lucky/admin/good/list').then((res) => {
        _this.TableData = res.data.data
        _this.showTableData = _this.showGood()
        _this.totalPage = _this.TableData.length
      })
    },
    // 获取商品所有图片
    getImage (goodId) {
      const _this = this
      this.$axios.get('/lucky/admin/good/images', {
        params: {
          'goodId': goodId
        }
      }).then((res) => {
        _this.imageUrlList = res.data.data
      })
    },
    handleAvatarSuccess (res, file) {
      this.imageUrlList.push(res.data)
      this.addUrlList.push(res.data)
    },
    // 确认按钮
    addGoodImage (row) {
      const _this = this
      if (this.addUrlList.length > 0) {
        let index = 0
        let type = 1
        let goodImageAOList = this.addUrlList.map((url) => {
          if (_this.imageUrlList.length <= 1 && index === 0) {
            type = 0
          } else {
            type = 1
          }
          index++
          return {
            'goodId': row.id,
            'type': type,
            'url': url,
            'creatorName': this.adminInfo
          }
        })
        this.addUrlList = []
        this.$axios.post('/lucky/admin/good/images', goodImageAOList).then((res) => {
          if (res.data.code === 200) {
            _this.$message.success(res.data.msg)
            _this.addGoodImageCancel()
          } else {
            _this.$message.error(res.data.msg)
          }
        }).catch(() => {
          _this.$message.error('商品图片上传出错!')
        })
      } else {
        this.$message.error('图片为空!')
      }
    },
    beforeAvatarUpload (file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isLt2M
    },
    // 取消按钮
    addGoodImageCancel () {
      this.addGoodImageVisible = false
      this.imageUrlList = []
      this.addUrlList = []
    },
    // 商品图片按钮
    imageInfo (row) {
      this.addGoodImageVisible = true
      this.getImage(row.id)
    },
    // 显示当前页的分类数据
    showGood () {
      return this.TableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  }
}
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .el-upload--picture-card {
    background-color: #fbfdff;
    border: 1px dashed #c0ccda;
    border-radius: 6px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 178px;
    height: 178px;
    line-height: 176px;
    vertical-align: top;
  }
</style>
