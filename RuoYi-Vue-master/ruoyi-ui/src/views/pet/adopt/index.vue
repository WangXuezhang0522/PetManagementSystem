<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="类别" prop="category">
        <el-input v-model="queryParams.category" placeholder="请输入类别" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="queryParams.age" placeholder="请输入年龄" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option v-for="dict in dict.type.pet_gender" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="领养费用" prop="adoptionExpenses">
        <el-input
          v-model="queryParams.adoptionExpenses"
          placeholder="请输入领养费用"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择宠物状态" clearable>
          <el-option
            v-for="dict in dict.type.pet_adopt"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建者姓名" prop="createByName">
        <el-input
          v-model="queryParams.createByName"
          placeholder="请输入创建者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['pet:adopt:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['pet:adopt:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['pet:adopt:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['pet:adopt:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="adoptList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pet_gender" :value="scope.row.sex" />
        </template>
      </el-table-column>
      <el-table-column label="类别" align="center" prop="category" />
      <el-table-column label="照片" align="center" prop="img" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.img" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="性情" align="center" prop="temperament" />
      <el-table-column label="疫苗情况" align="center" prop="vaccinum" />
      <el-table-column label="领养费用" align="center" prop="adoptionExpenses" />
      <el-table-column label="宠物状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pet_adopt" :value="scope.row.state" />
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createByName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-s-check" @click="handleAdopt(scope.row)"
            v-hasPermi="['pet:application:add']" v-show="scope.row.state == '0'">领养</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['pet:adopt:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['pet:adopt:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改宠物领养对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别" style="width: 100%;">
            <el-option v-for="dict in dict.type.pet_gender" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类别" prop="category">
          <el-input v-model="form.category" placeholder="请输入类别" />
        </el-form-item>
        <el-form-item label="照片" prop="img">
          <image-upload v-model="form.img" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="性情" prop="temperament">
          <el-input v-model="form.temperament" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="疫苗情况" prop="vaccinum">
          <el-input v-model="form.vaccinum" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="领养费用" prop="adoptionExpenses">
          <el-input v-model="form.adoptionExpenses" placeholder="请输入领养费用" />
        </el-form-item>
        <!-- <el-form-item label="宠物状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择宠物状态">
            <el-option
              v-for="dict in dict.type.pet_adopt"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改宠物领养申请对话框 -->
    <el-dialog :title="title1" :visible.sync="open1" width="500px" append-to-body>
      <el-form ref="form1" :model="form1" :rules="rules1" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form1.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form1.sex" placeholder="请选择性别" style="width: 100%;">
            <el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phonenumber">
          <el-input v-model="form1.phonenumber" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form1.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form1.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="领养费用" prop="adoptionExpenses">
          <el-input v-model="form1.adoptionExpenses" placeholder="请输入领养费用" :disabled='true' />
        </el-form-item>
        <el-form-item label="支付状态" prop="paymentStatus">
          <el-select v-model="form1.paymentStatus" placeholder="请选择支付状态" style="width: 100%;" :disabled='true'>
            <el-option v-for="dict in dict.type.payment_status" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核状态" prop="state">
          <el-select v-model="form1.state" placeholder="请选择审核状态" style="width: 100%;" :disabled='true'>
            <el-option v-for="dict in dict.type.review_status" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form1.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm1">确定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listAdopt, getAdopt, delAdopt, addAdopt, updateAdopt } from "@/api/pet/adopt";
import { addApplication } from "@/api/pet/application";

export default {
  name: "Adopt",
  dicts: ['pet_adopt', 'review_status', 'sys_user_sex', 'payment_status', 'pet_gender'],
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
      // 宠物领养表格数据
      adoptList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        category: null,
        img: null,
        age: null,
        sex: null,
        temperament: null,
        vaccinum: null,
        adoptionExpenses: null,
        state: null,
        createByName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        category: [
          { required: true, message: "类别不能为空", trigger: "blur" }
        ],
        img: [
          { required: true, message: "照片不能为空", trigger: "blur" }
        ],
        age: [
          { required: true, message: "年龄不能为空", trigger: "blur" }
        ],
        temperament: [
          { required: true, message: "性情不能为空", trigger: "blur" }
        ],
        vaccinum: [
          { required: true, message: "疫苗情况不能为空", trigger: "blur" }
        ],
        adoptionExpenses: [
          { required: true, message: "领养费用不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "宠物状态不能为空", trigger: "change" }
        ],
      },
      // 弹出层标题
      title1: "",
      // 是否显示弹出层
      open1: false,
      // 表单参数
      form1: {},
      // 表单校验
      rules1: {
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
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询宠物领养列表 */
    getList() {
      this.loading = true;
      listAdopt(this.queryParams).then(response => {
        this.adoptList = response.rows;
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
        name: null,
        sex: null,
        category: null,
        img: null,
        age: null,
        temperament: null,
        vaccinum: null,
        adoptionExpenses: null,
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
      this.title = "添加宠物领养";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAdopt(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物领养";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAdopt(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdopt(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除宠物领养编号为"' + ids + '"的数据项？').then(function () {
        return delAdopt(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pet/adopt/export', {
        ...this.queryParams
      }, `adopt_${new Date().getTime()}.xlsx`)
    },
    /** 领养按钮操作 */
    handleAdopt(row) {
      this.reset1();
      this.open1 = true;
      this.title1 = "添加宠物领养申请";
      this.form1.adoptId = row.id;
      this.form1.adoptionExpenses = row.adoptionExpenses;
      this.form1.paymentStatus = '0';
      this.form1.state = '0';
    },
    // 取消按钮
    cancel1() {
      this.open1 = false;
      this.reset1();
    },
    // 表单重置
    reset1() {
      this.form1 = {
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
      this.resetForm("form1");
    },
    /** 提交按钮 */
    submitForm1() {
      this.$refs["form1"].validate(valid => {
        if (valid) {
          addApplication(this.form1).then(response => {
            this.$modal.msgSuccess("领养申请成功");
            this.open1 = false;
            this.getList();
            // this.$router.push({ path: '/order/application' });
          });
        }
      });
    },
  }
};
</script>
