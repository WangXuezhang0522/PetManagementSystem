<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderNo">
        <el-input v-model="queryParams.orderNo" placeholder="请输入订单编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="收货地址id" prop="sysUserAddressId">
        <el-input v-model="queryParams.sysUserAddressId" placeholder="请输入收货地址id" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="订单价格" prop="orderPrice">
        <el-input v-model="queryParams.orderPrice" placeholder="请输入订单价格" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="付款时间" prop="fksjTime">
        <el-date-picker clearable v-model="queryParams.fksjTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择付款时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="商品id" prop="productId">
        <el-input v-model="queryParams.productId" placeholder="请输入商品id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="数量" prop="number">
        <el-input v-model="queryParams.number" placeholder="请输入数量" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="单价" prop="unitPrice">
        <el-input v-model="queryParams.unitPrice" placeholder="请输入单价" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input v-model="queryParams.price" placeholder="请输入价格" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态(1.待发货,2.已发货,3.已签收)" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态(1.待发货,2.已发货,3.已签收)" clearable>
          <el-option v-for="dict in dict.type.order_state" :key="dict.value" :label="dict.label" :value="dict.value" />
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
          v-hasPermi="['order:info:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['order:info:edit']">修改</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['order:info:remove']">删除</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['order:info:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="订单编号" align="center" prop="orderNo" />
      <el-table-column label="商品名称" align="center" prop="storeCommodity.name" />
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="单价" align="center" prop="unitPrice" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_state" :value="scope.row.state" />
        </template>
      </el-table-column>
      <el-table-column label="付款时间" align="center" prop="fksjTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.fksjTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收货地址" align="center" prop="sysUserAddressId">
        <template slot-scope="scope">
          <span>{{ scope.row.sysUserAddress.province }}-{{ scope.row.sysUserAddress.city }}-{{
      scope.row.sysUserAddress.area }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createByName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleDetail(scope.row)"
            v-hasPermi="['order:info:query']">详情</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['order:info:edit']">修改</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['order:info:remove']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择状态">
            <el-option v-for="dict in dict.type.order_state" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="titleOrder" :visible.sync="openOrder" width="800px" append-to-body>
      <el-descriptions title="订单信息">
        <el-descriptions-item label="订单编号">{{ orderData.orderNo }}</el-descriptions-item>
        <el-descriptions-item label="商品名称">{{ orderData.storeCommodity.name }}</el-descriptions-item>
        <el-descriptions-item label="数量">{{ orderData.number }}</el-descriptions-item>
        <el-descriptions-item label="单价">{{ orderData.unitPrice }}</el-descriptions-item>
        <el-descriptions-item label="价格">{{ orderData.price }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <dict-tag :options="dict.type.order_state" :value="orderData.state" />
        </el-descriptions-item>
        <el-descriptions-item label="付款时间">
          {{ parseTime(orderData.fksjTime, '{y}-{m}-{d} {h}:{i}:{s}') }}
        </el-descriptions-item>
        <el-descriptions-item label="创建者">{{ orderData.createByName }}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="收货地址">
        <el-descriptions-item label="收件人">{{ orderData.sysUserAddress.recipient }}</el-descriptions-item>
        <el-descriptions-item label="手机号">{{ orderData.sysUserAddress.phonenumber }}</el-descriptions-item>
        <el-descriptions-item label="所在省">{{ orderData.sysUserAddress.province }}</el-descriptions-item>
        <el-descriptions-item label="所在市">{{ orderData.sysUserAddress.city }}</el-descriptions-item>
        <el-descriptions-item label="所在区">{{ orderData.sysUserAddress.area }}</el-descriptions-item>
        <el-descriptions-item label="详细地址"> {{ orderData.sysUserAddress.address }} </el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="商品信息">
        <el-descriptions-item label="商品名称">{{ orderData.storeCommodity.name }}</el-descriptions-item>
        <el-descriptions-item label="价格">{{ orderData.storeCommodity.price }}</el-descriptions-item>
        <el-descriptions-item label="类型">
          <dict-tag :options="dict.type.store_type" :value="orderData.storeCommodity.type" />
        </el-descriptions-item>
        <el-descriptions-item label="分类">{{ orderData.storeCommodity.category }}</el-descriptions-item>
        <el-descriptions-item label="图片">
          <image-preview :src="orderData.storeCommodity.img" :width="50" :height="50" />
        </el-descriptions-item>
        <el-descriptions-item label="品牌">{{ orderData.storeCommodity.brand }}</el-descriptions-item>
        <el-descriptions-item label="规格">{{ orderData.storeCommodity.specifications }}</el-descriptions-item>
        <el-descriptions-item label="保质期">
          {{ parseTime(orderData.storeCommodity.bzqTime, '{y}-{m}-{d}') }}
        </el-descriptions-item>
        <el-descriptions-item label="生产日期">
          {{ parseTime(orderData.storeCommodity.scrqTime, '{y}-{m}-{d}') }}
        </el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="商家信息">
        <el-descriptions-item label="商店名称">{{ orderData.storeInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="经营范围">{{ orderData.storeInfo.businessScope }}</el-descriptions-item>
        <el-descriptions-item label="联系人">{{ orderData.storeInfo.contacts }}</el-descriptions-item>
        <el-descriptions-item label="联系电话">{{ orderData.storeInfo.phonenumber }}</el-descriptions-item>
        <el-descriptions-item label="地址">{{ orderData.storeInfo.address }}</el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelOrder">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/order/info";

export default {
  name: "Info",
  dicts: ['order_state', 'store_type'],
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
      // 订单管理表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        sysUserAddressId: null,
        orderPrice: null,
        fksjTime: null,
        productId: null,
        number: null,
        unitPrice: null,
        price: null,
        state: null,
        createByName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "编号不能为空", trigger: "blur" }
        ],
        orderNo: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
        sysUserAddressId: [
          { required: true, message: "收货地址id不能为空", trigger: "blur" }
        ],
        orderPrice: [
          { required: true, message: "订单价格不能为空", trigger: "blur" }
        ],
        fksjTime: [
          { required: true, message: "付款时间不能为空", trigger: "blur" }
        ],
        productId: [
          { required: true, message: "商品id不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        unitPrice: [
          { required: true, message: "单价不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "状态(1.待发货,2.已发货,3.已签收)不能为空", trigger: "change" }
        ],
      },
      openOrder: false,
      titleOrder: "",
      orderData: {
        storeCommodity: {
          name: ""
        },
        sysUserAddress: {
          recipient: "",
          phonenumber: "",
          province: "",
          city: "",
          area: "",
          address: ""
        },
        storeCommodity: {
          price: "",
          type: "",
          category: "",
          img: "",
          brand: "",
          specifications: "",
          bzqTime: "",
          scrqTime: ""
        },
        storeInfo: {
          name: "",
          businessScope: "",
          contacts: "",
          phonenumber: "",
          address: ""
        }
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询订单管理列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
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
        orderNo: null,
        sysUserAddressId: null,
        orderPrice: null,
        fksjTime: null,
        productId: null,
        number: null,
        unitPrice: null,
        price: null,
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
      this.title = "添加订单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除订单管理编号为"' + ids + '"的数据项？').then(function () {
        return delInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('order/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    },
    /** 详情按钮操作 */
    handleDetail(row) {
      this.resetOrder();
      const id = row.id || this.ids
      console.log(id);
      getInfo(id).then(response => {
        this.orderData = response.data;
        console.log(this.orderData);
        this.openOrder = true;
        this.titleOrder = "查看订单详情";
      });
    },
    // 取消按钮
    cancelOrder() {
      this.openOrder = false;
      this.resetOrder();
    },
    // 表单重置
    resetOrder() {
      this.orderData = {
        storeCommodity: {
          name: ""
        },
        sysUserAddress: {
          recipient: "",
          phonenumber: "",
          province: "",
          city: "",
          area: "",
          address: ""
        },
        storeCommodity: {
          price: "",
          type: "",
          category: "",
          img: "",
          brand: "",
          specifications: "",
          bzqTime: "",
          scrqTime: ""
        },
        storeInfo: {
          name: "",
          businessScope: "",
          contacts: "",
          phonenumber: "",
          address: ""
        }
      };
    },
  }
};
</script>
