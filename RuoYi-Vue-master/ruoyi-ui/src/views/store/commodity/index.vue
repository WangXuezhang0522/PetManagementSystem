<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="价格" prop="price">
        <el-input v-model="queryParams.price" placeholder="请输入价格" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable>
          <el-option v-for="dict in dict.type.store_type" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="品牌" prop="brand">
        <el-input v-model="queryParams.brand" placeholder="请输入品牌" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="规格" prop="specifications">
        <el-input v-model="queryParams.specifications" placeholder="请输入规格" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="保质期" prop="bzqTime">
        <el-date-picker clearable v-model="queryParams.bzqTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择保质期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="生产日期" prop="scrqTime">
        <el-date-picker clearable v-model="queryParams.scrqTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择生产日期">
        </el-date-picker>
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
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['store:commodity:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['store:commodity:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['store:commodity:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['store:commodity:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commodityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="商店" align="center" prop="storeInfoName" />
      <el-table-column label="商品名称" align="center" prop="name" />
      <el-table-column label="图片" align="center" prop="img" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.img" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.store_type" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="category" />
      <el-table-column label="品牌" align="center" prop="brand" />
      <el-table-column label="规格" align="center" prop="specifications" />
      <el-table-column label="保质期" align="center" prop="bzqTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bzqTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产日期" align="center" prop="scrqTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.scrqTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createByName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['store:commodity:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['store:commodity:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改商品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商店" prop="storeInfoId">
          <el-select v-model="form.storeInfoId" placeholder="请选择商店" style="width: 100%;">
            <el-option v-for="item in storeInfoList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="图片" prop="img">
          <image-upload v-model="form.img" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型" style="width: 100%;">
            <el-option v-for="dict in dict.type.store_type" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="type" v-show="form.type == '0'">
          <el-select v-model="form.category" placeholder="请选择分类" style="width: 100%;">
            <el-option v-for="item in listProduct" :key="item.id" :label="item.name" :value="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="type" v-show="form.type == '1'">
          <el-select v-model="form.category" placeholder="请选择分类" style="width: 100%;">
            <el-option v-for="item in listPet" :key="item.id" :label="item.name" :value="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="规格" prop="specifications">
          <el-input v-model="form.specifications" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="保质期" prop="bzqTime">
          <el-date-picker clearable v-model="form.bzqTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择保质期"
            style="width: 100%;">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="生产日期" prop="scrqTime">
          <el-date-picker clearable v-model="form.scrqTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择生产日期"
            style="width: 100%;">
          </el-date-picker>
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
import { listCommodity, getCommodity, delCommodity, addCommodity, updateCommodity } from "@/api/store/commodity";
import { listPetClassification } from "@/api/store/petClassification";
import { listProductClassification } from "@/api/store/productClassification";
import { listInfo } from "@/api/store/info";

export default {
  name: "Commodity",
  dicts: ['store_type'],
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
      // 商品信息表格数据
      commodityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        price: null,
        type: null,
        category: null,
        img: null,
        brand: null,
        specifications: null,
        bzqTime: null,
        scrqTime: null,
        createByName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "编号不能为空", trigger: "blur" }
        ],
        storeInfoId: [
          { required: true, message: "商店不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ],
        category: [
          { required: true, message: "类别不能为空", trigger: "change" }
        ],
        img: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        brand: [
          { required: true, message: "品牌不能为空", trigger: "blur" }
        ],
        specifications: [
          { required: true, message: "规格不能为空", trigger: "blur" }
        ],
        bzqTime: [
          { required: true, message: "保质期不能为空", trigger: "blur" }
        ],
        scrqTime: [
          { required: true, message: "生产日期不能为空", trigger: "blur" }
        ],
      },
      listProduct: [],
      listPet: [],
      storeInfoList: []
    };
  },
  created() {
    this.getListProduct();
    this.getListPet();
    this.getStoreInfoList();
    this.getList();
  },
  methods: {
    /** 获取商店数据 */
    getStoreInfoList() {
      listInfo(this.queryParams).then(response => {
        this.storeInfoList = response.rows;
      });
    },
    /** 查询商品分类信息 */
    getListProduct() {
      listProductClassification().then(response => {
        this.listProduct = response.rows;
      });
    },
    /** 查询宠物信息 */
    getListPet() {
      listPetClassification().then(response => {
        this.listPet = response.rows;
      });
    },
    /** 查询商品信息列表 */
    getList() {
      this.loading = true;
      listCommodity(this.queryParams).then(response => {
        this.commodityList = response.rows;
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
        price: null,
        type: null,
        category: null,
        img: null,
        brand: null,
        specifications: null,
        bzqTime: null,
        scrqTime: null,
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
      this.title = "添加商品信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCommodity(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCommodity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCommodity(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除商品信息编号为"' + ids + '"的数据项？').then(function () {
        return delCommodity(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('store/commodity/export', {
        ...this.queryParams
      }, `commodity_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
