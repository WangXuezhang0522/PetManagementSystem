<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="领养宠物id" prop="adoptId">
        <el-input v-model="queryParams.adoptId" placeholder="请输入领养宠物id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="手机号" prop="phonenumber">
        <el-input v-model="queryParams.phonenumber" placeholder="请输入手机号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="queryParams.email" placeholder="请输入邮箱" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="领养费用" prop="adoptionExpenses">
        <el-input v-model="queryParams.adoptionExpenses" placeholder="请输入领养费用" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="支付状态(0未支付,1已支付)" prop="paymentStatus">
        <el-select v-model="queryParams.paymentStatus" placeholder="请选择支付状态(0未支付,1已支付)" clearable>
          <el-option v-for="dict in dict.type.payment_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
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
          v-hasPermi="['pet:application:add']">新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['pet:application:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['pet:application:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['pet:application:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applicationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编码" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="petAdopt.name" />
      <el-table-column label="照片" align="center" prop="petAdopt.img" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.petAdopt.img" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex" />
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" prop="phonenumber" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="领养费用" align="center" prop="adoptionExpenses" />
      <el-table-column label="支付状态" align="center" prop="paymentStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.payment_status" :value="scope.row.paymentStatus" />
        </template>
      </el-table-column>
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
            v-hasPermi="['pet:application:examine']" v-show="scope.row.state == '0'">审核成功</el-button>
          <el-button size="mini" type="text" icon="el-icon-s-check" @click="handleExamine(scope.row, '2')"
            v-hasPermi="['pet:application:examine']" v-show="scope.row.state == '0'">审核失败</el-button>
          <el-button size="mini" type="text" icon="el-icon-s-check" @click="handlePayment(scope.row)"
            v-hasPermi="['pet:application:payment']"
            v-show="scope.row.paymentStatus == '0' && scope.row.state == '1'">支付</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['pet:application:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['pet:application:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改宠物领养申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别" style="width: 100%;">
            <el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
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
        <el-form-item label="领养费用" prop="adoptionExpenses">
          <el-input v-model="form.adoptionExpenses" placeholder="请输入领养费用" />
        </el-form-item>
        <el-form-item label="支付状态" prop="paymentStatus">
          <el-select v-model="form.paymentStatus" placeholder="请选择支付状态" style="width: 100%;">
            <el-option v-for="dict in dict.type.payment_status" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择审核状态" style="width: 100%;">
            <el-option v-for="dict in dict.type.review_status" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
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
import { listApplication, getApplication, delApplication, addApplication, updateApplication, updateExamine, getPayment } from "@/api/pet/application";

export default {
  name: "Application",
  dicts: ['review_status', 'sys_user_sex', 'payment_status'],
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
      showSearch: false,
      // 总条数
      total: 0,
      // 宠物领养申请表格数据
      applicationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        adoptId: null,
        name: null,
        sex: null,
        phonenumber: null,
        email: null,
        address: null,
        adoptionExpenses: null,
        paymentStatus: null,
        state: null,
        createByName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "编码不能为空", trigger: "blur" }
        ],
        adoptId: [
          { required: true, message: "领养宠物id不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "姓名不能为空", trigger: "change" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "change" }
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
        adoptionExpenses: [
          { required: true, message: "领养费用不能为空", trigger: "blur" }
        ],
        paymentStatus: [
          { required: true, message: "支付状态(0未支付,1已支付)不能为空", trigger: "change" }
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
    /** 查询宠物领养申请列表 */
    getList() {
      this.loading = true;
      listApplication(this.queryParams).then(response => {
        this.applicationList = response.rows;
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
        adoptId: null,
        name: null,
        sex: null,
        phonenumber: null,
        email: null,
        address: null,
        adoptionExpenses: null,
        paymentStatus: null,
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
      this.title = "添加宠物领养申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApplication(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物领养申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApplication(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApplication(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除宠物领养申请编号为"' + ids + '"的数据项？').then(function () {
        return delApplication(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pet/application/export', {
        ...this.queryParams
      }, `application_${new Date().getTime()}.xlsx`)
    },
    /** 审核按钮操作 */
    handleExamine(row, state) {
      const id = row.id || this.ids
      getApplication(id).then(response => {
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
    /** 支付按钮操作 */
    handlePayment(row) {
      const id = row.id || this.ids;
      this.$modal.confirm('是否确认支付宠物领养申请编号为"' + id + '"的数据项？').then(function () {
        return getPayment(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("支付成功");
      }).catch(() => { });
    }
  }
};
</script>
