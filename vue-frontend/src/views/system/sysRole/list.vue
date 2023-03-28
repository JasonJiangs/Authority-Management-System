<template>
  <div class="app-container">

    <!--查询表单-->
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="24">
            <el-form-item label="Role Name">
              <el-input style="width: 100%" v-model="searchObj.roleName" placeholder="Role Name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini" :loading="loading" @click="fetchData()">Search</el-button>
          <!-- 工具条 -->
          <el-button type="success" icon="el-icon-plus" size="mini" @click="add">Add</el-button>
          <el-button  type="success"  size="mini" @click="batchRemove()" >Batch Delete</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
        </el-row>
      </el-form>
    </div>

    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%;margin-top: 10px;"
      @selection-change="handleSelectionChange">

      <el-table-column type="selection"/>

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="roleName" label="Role Name" />
      <el-table-column prop="roleCode" label="Role Code" />
      <el-table-column prop="createTime" label="Creation Time" width="160"/>
      <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="Edit"/>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)" title="Delete"/>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />
    <el-dialog title="Add/Edit" :visible.sync="dialogVisible" width="40%" >
      <el-form ref="dataForm" :model="sysRole" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="Role Name">
          <el-input v-model="sysRole.roleName"/>
        </el-form-item>
        <el-form-item label="Role Code">
          <el-input v-model="sysRole.roleCode"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">Cancel</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">Confirm</el-button>
      </span>
    </el-dialog>
  </div>

</template>



<script>
import api from "@/api/system/sysRole"

export default {
  // initialized data
  data() {
    return {
      list: [],
      page: 1,
      limit: 10,
      total: 0,
      // 条件对象
      searchObj: {},
      // 封装表单角色数据
      sysRole: {},
      // 是否弹框
      dialogVisible: false,
      multipleSelection: []// 批量删除选中的记录列表
    }
  },
  // execute before rendering
  created () {
    this.fetchData()
  },
  // execution
  methods: {
    add(){
      this.dialogVisible = true
    },
    // conditional pagnition
    fetchData(current=1) {
      this.page = current
      api.getPageList(this.page, this.limit, this.searchObj)
      .then(response => {
        this.list = response.data.records
        this.total = response.data.total
      })
    },

    // 根据id删除数据
    removeDataById(id) {
      // debugger
      this.$confirm('It is a permanent delete, confirming continue?', 'Warn', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return api.removeById(id)
      }).then((response) => {
        // update information
        this.fetchData(this.page)
        // tips
        this.$message.success(response.message || 'Delete Successfully')
      })
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true // 防止表单重复提交
      if (!this.sysRole.id) {
        this.saveData()
      } else {
        this.updateData()
      }
    },
    // 新增
    saveData() {
      api.save(this.sysRole).then(response => {
        // 提示
        this.$message.success(response.message || '操作成功')
        // 关闭弹框
        this.dialogVisible = false
        // 刷新页面
        this.fetchData(this.page)
      })
    },
    updateData() {
      api.updateById(this.sysRole).then(response => {
        this.$message.success(response.message || '操作成功')
        this.dialogVisible = false
        this.fetchData(this.page)
      })
    },

    edit(id) {
      this.dialogVisible = true
      this.fetchDataById(id)
    },

    fetchDataById(id) {
      api.getById(id).then(response => {
        this.sysRole = response.data
      })
    },

    // 当多选选项发生变化的时候调用
    handleSelectionChange(selection) {
      console.log(selection)
      this.multipleSelection = selection
    },
    // 批量删除
    batchRemove() {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('Choose deleted records！')
        return
      }
      this.$confirm('It is a permanent delete, confirming continue', 'Warn', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        // 点击确定，远程调用ajax
        // 遍历selection，将id取出放入id列表
        var idList = []
        this.multipleSelection.forEach(item => {
          idList.push(item.id)
        })
        // 调用api
        return api.batchRemove(idList)
      }).then((response) => {
        this.fetchData()
        this.$message.success(response.message)
      })
    }
  }
}

</script>
