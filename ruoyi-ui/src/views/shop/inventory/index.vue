<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品类别" prop="productCategory">
        <el-select v-model="queryParams.productCategory" placeholder="请选择商品类别" clearable>
          <el-option
            v-for="dict in dict.type.product_category"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品品牌" prop="productBrand">
        <el-select v-model="queryParams.productBrand" placeholder="请选择商品品牌" clearable>
          <el-option
            v-for="dict in dict.type.product_brand"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="生产日期" prop="manufacturingDate">
        <el-date-picker clearable
          v-model="queryParams.manufacturingDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择生产日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="有效期" prop="expirationDate">
        <el-date-picker clearable
          v-model="queryParams.expirationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择有效期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="保质期" prop="qualityGuaranteePeriod">
        <el-input
          v-model="queryParams.qualityGuaranteePeriod"
          placeholder="请输入保质期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购入日期" prop="buyDate">
        <el-date-picker clearable
          v-model="queryParams.buyDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择购入日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['shop:inventory:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:inventory:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:inventory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:inventory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inventoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="商品类别" align="center" prop="productCategory">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.product_category" :value="scope.row.productCategory"/>
        </template>
      </el-table-column>
      <el-table-column label="商品品牌" align="center" prop="productBrand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.product_brand" :value="scope.row.productBrand"/>
        </template>
      </el-table-column>
      <el-table-column label="商品名称" align="center" prop="productName" />
<!--      <el-table-column label="容量" align="center" v-if="false" prop="capacity" />-->
      <el-table-column label="容量" width="100" align="center" prop="capacity,capacityUnit">
        <template slot-scope="scope">
<!--          <span v-text="scope.row.capacity">-->
           {{scope.row.capacity}}
          <dict-tag :options="dict.type.porduct_capacity_unit" :value="scope.row.capacityUnit" style="display: inline"/>
<!--          </span>-->

        </template>
      </el-table-column>
<!--      <el-table-column label="数量" align="center" prop="number" />-->
      <el-table-column label="数量" width="100" align="center" prop="number,numUnit">
        <template slot-scope="scope">
          {{scope.row.number}}
          <dict-tag :options="dict.type.product_num_unit" :value="scope.row.numUnit" style="display: inline"/>
        </template>
      </el-table-column>
      <el-table-column label="生产日期" align="center" prop="manufacturingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.manufacturingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="有效期" align="center" prop="expirationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="保质期" align="center" prop="qualityGuaranteePeriod" />
      <el-table-column label="购入日期" align="center" prop="buyDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buyDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:inventory:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:inventory:edit']"
          >出库</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:inventory:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商品库存信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品品牌" prop="productBrand">
              <el-select v-model="form.productBrand" placeholder="请选择商品品牌">
                <el-option
                  v-for="dict in dict.type.product_brand"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品类别" prop="productCategory">
              <el-select v-model="form.productCategory" placeholder="请选择商品类别">
                <el-option
                  v-for="dict in dict.type.product_category"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="商品名称" prop="productName">
              <el-input v-model="form.productName" placeholder="请输入商品名称" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="容量" prop="capacity">
              <el-input v-model="form.capacity" style="width: 100px" placeholder="请输入容量" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
<!--            <el-form-item label="" prop="capacityUnit">-->
              <el-select v-model="form.capacityUnit" style="width: 100px" placeholder="容量单位">
                <el-option
                  v-for="dict in dict.type.porduct_capacity_unit"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
<!--            </el-form-item>-->
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="购入日期" prop="buyDate">
              <el-date-picker clearable
                              v-model="form.buyDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择购入日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="数量" prop="number">
              <el-input v-model="form.number" style="width: 100px" placeholder="请输入数量" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
<!--            <el-form-item label="数量单位" prop="numUnit">-->
              <el-select v-model="form.numUnit" style="width: 100px" placeholder="数量单位">
                <el-option
                  v-for="dict in dict.type.product_num_unit"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
<!--            </el-form-item>-->
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="生产日期" prop="manufacturingDate">
              <el-date-picker clearable
                              v-model="form.manufacturingDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择生产日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="有效期" prop="expirationDate">
              <el-date-picker clearable
                              v-model="form.expirationDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择有效期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="保质期" prop="qualityGuaranteePeriod">
              <el-input v-model="form.qualityGuaranteePeriod" placeholder="请输入保质期" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
          </el-col>
          <el-col :span="12">
          </el-col>
        </el-row>






<!--        <el-form-item label="删除标志" prop="delFlag">-->
<!--          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="数据范围" prop="dataScope">-->
<!--          <el-input v-model="form.dataScope" placeholder="请输入数据范围" />-->
<!--        </el-form-item>-->
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
import { listInventory, getInventory, delInventory, addInventory, updateInventory } from "@/api/shop/inventory";

export default {
  name: "Inventory",
  dicts: ['product_category', 'product_num_unit', 'porduct_capacity_unit', 'product_brand'],
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
      // 商品库存信息表格数据
      inventoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productCategory: null,
        productBrand: null,
        productName: null,
        capacity: null,
        capacityUnit: null,
        number: null,
        numUnit: null,
        manufacturingDate: null,
        expirationDate: null,
        qualityGuaranteePeriod: null,
        buyDate: null,
        status: null,
        dataScope: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        capacity: [
          { required: true, message: "容量不能为空", trigger: "blur" }
        ],
        capacityUnit: [
          { required: true, message: "容量单位不能为空", trigger: "change" }
        ],
        number: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        numUnit: [
          { required: true, message: "数量单位不能为空", trigger: "change" }
        ],
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品库存信息列表 */
    getList() {
      this.loading = true;
      listInventory(this.queryParams).then(response => {
        this.inventoryList = response.rows;
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
        productCategory: null,
        productBrand: null,
        productName: null,
        capacity: null,
        capacityUnit: null,
        number: null,
        numUnit: null,
        manufacturingDate: null,
        expirationDate: null,
        qualityGuaranteePeriod: null,
        buyDate: null,
        delFlag: null,
        status: null,
        dataScope: null,
        createBy: null,
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品库存信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInventory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品库存信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInventory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInventory(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除商品库存信息编号为"' + ids + '"的数据项？').then(function() {
        return delInventory(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shop/inventory/export', {
        ...this.queryParams
      }, `inventory_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
