<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" style="width: 100%;">
          <el-option v-for="dict in dict.type.store_type" :key="dict.value" :label="dict.label"
            :value="dict.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品分类" prop="category" v-show="queryParams.type == '0'">
        <el-select v-model="queryParams.category" placeholder="请选择分类" style="width: 100%;">
          <el-option v-for="item in listProductAll" :key="item.id" :label="item.name" :value="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="宠物分类" prop="category" v-show="queryParams.type == '1'">
        <el-select v-model="queryParams.category" placeholder="请选择分类" style="width: 100%;">
          <el-option v-for="item in listPetAll" :key="item.id" :label="item.name" :value="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="commodityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="商品名称" align="center" prop="name" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.store_type" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="category" />
      <el-table-column label="图片" align="center" prop="img" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.img" :width="50" :height="50" />
        </template>
      </el-table-column>
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
          <el-button size="mini" type="text" icon="el-icon-s-goods" @click="handleMyCart(scope.row)"
            v-hasPermi="['pet:store:add']">加入购物车</el-button>
          <el-button size="mini" type="text" icon="el-icon-star-on" @click="handleCollect(scope.row)"
            v-hasPermi="['system:collect:add']">收藏</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改商品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入商品名称" :disabled='true' />
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价" :disabled='true' />
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入数量" />
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
import { listCommodity, getCommodity, addCommodity } from "@/api/pet/store";
import { listPetClassificationAll } from "@/api/store/petClassification";
import { listProductClassificationAll } from "@/api/store/productClassification";
import { addCollect } from "@/api/system/collect";

export default {
  name: "Store",
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
        type: "",
        category: ""
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "编号不能为空", trigger: "blur" }
        ],
        productId: [
          { required: true, message: "商品id不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
      },
      listProductAll: [],
      listPetAll: [],
    };
  },
  created() {
    this.getListProductAll();
    this.getListPetAll();
    this.getList();
  },
  methods: {
    /** 查询商品分类信息 */
    getListProductAll() {
      listProductClassificationAll().then(response => {
        this.listProductAll = response.rows;
      });
    },
    /** 查询宠物信息 */
    getListPetAll() {
      listPetClassificationAll().then(response => {
        this.listPetAll = response.rows;
      });
    },
    /** 查询商品信息列表 */
    getList() {
      this.loading = true;
      console.log(this.queryParams);
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
        storeInfoId: null,
        productId: null,
        productName: null,
        number: null,
        unitPrice: null,
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
    /** 加入购物车按钮操作 */
    handleMyCart(row) {
      this.reset();
      const id = row.id || this.ids
      getCommodity(id).then(response => {
        let data = response.data;
        this.form.storeInfoId = data.storeInfoId;
        this.form.productId = data.id;
        this.form.productName = data.name;
        this.form.unitPrice = data.price;
        this.form.number = '1';
        this.open = true;
        this.title = "添加购物车";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          addCommodity(this.form).then(response => {
            this.$modal.msgSuccess("添加成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
    handleCollect(row) {
      const id = row.id || this.ids
      let data = {
        productId: id,
      };
      addCollect(data).then(response => {
        this.$modal.msgSuccess("收藏成功");
        this.getList();
      });
    }
  }
};
</script>
