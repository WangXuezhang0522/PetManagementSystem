<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="机构名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入机构名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="联系人" prop="contacts">
        <el-input v-model="queryParams.contacts" placeholder="请输入联系人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号" prop="phonenumber">
        <el-input v-model="queryParams.phonenumber" placeholder="请输入手机号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="创建者姓名" prop="createByName">
        <el-input v-model="queryParams.createByName" placeholder="请输入创建者姓名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['pet:fosterInstitution:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['pet:fosterInstitution:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['pet:fosterInstitution:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['pet:fosterInstitution:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fosterInstitutionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="机构名称" align="center" prop="name" />
      <el-table-column label="联系人" align="center" prop="contacts" />
      <el-table-column label="手机号" align="center" prop="phonenumber" />
      <el-table-column label="经营范围" align="center" prop="businessScope" />
      <el-table-column label="创建者" align="center" prop="createByName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleFoster(scope.row)"
            v-hasPermi="['pet:fosterInstitution:foster']">寄养</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['pet:fosterInstitution:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['pet:fosterInstitution:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改宠物寄养机构对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="机构名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入机构名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="contacts">
          <el-input v-model="form.contacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="手机号" prop="phonenumber">
          <el-input v-model="form.phonenumber" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="经营范围" prop="businessScope">
          <el-input v-model="form.businessScope" type="textarea" placeholder="请输入内容" />
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

    <!-- 添加或修改宠物寄养申请对话框 -->
    <el-dialog :title="title1" :visible.sync="open1" width="500px" append-to-body>
      <el-form ref="form1" :model="form1" :rules="rules1" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form1.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form1.sex" placeholder="请选择性别" style="width: 100%;">
            <el-option v-for="dict in dict.type.pet_gender" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系人" prop="contacts">
          <el-input v-model="form1.contacts" placeholder="请输入联系人" />
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
        <el-button type="primary" @click="submitForm1">确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listFosterInstitution, getFosterInstitution, delFosterInstitution, addFosterInstitution, updateFosterInstitution } from "@/api/pet/fosterInstitution";
import { addFosterApplication } from "@/api/pet/fosterApplication";

export default {
  name: "FosterInstitution",
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
      // 宠物寄养机构表格数据
      fosterInstitutionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        contacts: null,
        phonenumber: null,
        businessScope: null,
        createByName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "编号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "机构名称不能为空", trigger: "blur" }
        ],
        contacts: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ],
        phonenumber: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        businessScope: [
          { required: true, message: "经营范围不能为空", trigger: "blur" }
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
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询宠物寄养机构列表 */
    getList() {
      this.loading = true;
      listFosterInstitution(this.queryParams).then(response => {
        this.fosterInstitutionList = response.rows;
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
        contacts: null,
        phonenumber: null,
        businessScope: null,
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
      this.title = "添加宠物寄养机构";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFosterInstitution(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物寄养机构";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFosterInstitution(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFosterInstitution(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除宠物寄养机构编号为"' + ids + '"的数据项？').then(function () {
        return delFosterInstitution(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pet/fosterInstitution/export', {
        ...this.queryParams
      }, `fosterInstitution_${new Date().getTime()}.xlsx`)
    },
    /** 寄养按钮操作 */
    handleFoster(row) {
      this.$confirm('是否确认寄养？').then(() => {
        this.reset1();
        this.open1 = true;
        this.title1 = "添加宠物寄养申请";
        this.form1.institutionId = row.id;
        this.form1.state = '0';
      }).catch(() => {
      });
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
      this.resetForm("form1");
    },
    /** 提交按钮 */
    submitForm1() {
      this.$refs["form1"].validate(valid => {
        if (valid) {
          addFosterApplication(this.form1).then(response => {
            this.$modal.msgSuccess("寄养申请成功");
            this.open1 = false;
            this.getList();
            // this.$router.push({ path: '/pet/fosterApplication' });
          });
        }
      });
    },
  }
};
</script>
