<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="机构id" prop="institutionId">
        <el-input v-model="queryParams.institutionId" placeholder="请输入机构id" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option v-for="dict in dict.type.pet_gender" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="联系人" prop="contacts">
        <el-input v-model="queryParams.contacts" placeholder="请输入联系人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号" prop="phonenumber">
        <el-input v-model="queryParams.phonenumber" placeholder="请输入手机号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="queryParams.email" placeholder="请输入邮箱" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="审核状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择审核状态" clearable>
          <el-option v-for="dict in dict.type.review_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建者姓名" prop="createByName">
        <el-input v-model="queryParams.createByName" placeholder="请输入创建者姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['pet:fosterApplication:add']">新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['pet:fosterApplication:edit']">修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['pet:fosterApplication:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['pet:fosterApplication:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fosterApplicationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="机构" align="center" prop="institutionName" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pet_gender" :value="scope.row.sex" />
        </template>
      </el-table-column>
      <el-table-column label="联系人" align="center" prop="contacts" />
      <el-table-column label="手机号" align="center" prop="phonenumber" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="审核状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.review_status" :value="scope.row.state" />
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createByName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-s-check" @click="handleExamine(scope.row, '1')"
            v-hasPermi="['pet:fosterApplication:examine']" v-show="scope.row.state == '0'">审核成功</el-button>
          <el-button size="mini" type="text" icon="el-icon-s-check" @click="handleExamine(scope.row, '2')"
            v-hasPermi="['pet:fosterApplication:examine']" v-show="scope.row.state == '0'">审核失败</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['pet:fosterApplication:edit']">修改</el-button> -->
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['pet:fosterApplication:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改宠物寄养申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="机构id" prop="institutionId">
          <el-input v-model="form.institutionId" placeholder="请输入机构id" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option v-for="dict in dict.type.pet_gender" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系人" prop="contacts">
          <el-input v-model="form.contacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="手机号" prop="phonenumber">
          <el-input v-model="form.phonenumber" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="审核状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择审核状态">
            <el-option v-for="dict in dict.type.review_status" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建者姓名" prop="createByName">
          <el-input v-model="form.createByName" placeholder="请输入创建者姓名" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFosterApplication, getFosterApplication, delFosterApplication, addFosterApplication, updateFosterApplication, updateExamine } from "@/api/pet/fosterApplication";

export default {
  name: "FosterApplication",
  dicts: ['review_status', 'pet_gender'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 宠物寄养申请表格数据
      fosterApplicationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        institutionId: null,
        name: null,
        sex: null,
        contacts: null,
        phonenumber: null,
        email: null,
        address: null,
        state: null,
        createByName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        institutionId: [
          { required: true, message: "机构id不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "change" }
        ],
        contacts: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ],
        phonenumber: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "地址不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "审核状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询宠物寄养申请列表 */
    getList() {
      this.loading = true;
      listFosterApplication(this.queryParams).then(response => {
        this.fosterApplicationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        institutionId: null,
        name: null,
        sex: null,
        contacts: null,
        phonenumber: null,
        email: null,
        address: null,
        state: null,
        createBy: null,
        createByName: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加宠物寄养申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFosterApplication(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物寄养申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFosterApplication(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFosterApplication(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除宠物寄养申请编号为"' + ids + '"的数据项？').then(function () {
        return delFosterApplication(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pet/fosterApplication/export', {
        ...this.queryParams
      }, `fosterApplication_${new Date().getTime()}.xlsx`)
    },
    /** 审核按钮操作 */
    handleExamine(row, state) {
      const id = row.id || this.ids
      getFosterApplication(id).then(response => {
        this.form = response.data;
        this.form.state = state;
        updateExamine(this.form).then(response => {
          if (state == '1') {
            this.$modal.msgSuccess("审核成功");
          } else {
            this.$modal.msgSuccess("审核失败");
          }
          this.getList();
        });
      });
    },
  }
};
</script>
