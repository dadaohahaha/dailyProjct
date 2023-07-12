<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="加油日期" prop="oilTime">
        <el-date-picker clearable
          v-model="queryParams.oilTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择加油日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="金额/元" prop="amount">
        <el-input
          v-model="queryParams.amount"
          placeholder="请输入金额/元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="油量/升L" prop="oilLitre">-->
<!--        <el-input-->
<!--          v-model="queryParams.oilLitre"-->
<!--          placeholder="请输入油量/升L"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="单价" prop="oilPrice">
        <el-input
          v-model="queryParams.oilPrice"
          placeholder="请输入单价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="加前剩余里程" prop="beforeRemainingMileage">-->
<!--        <el-input-->
<!--          v-model="queryParams.beforeRemainingMileage"-->
<!--          placeholder="请输入加前剩余里程"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="加后剩余里程" prop="afterRemainingMileage">-->
<!--        <el-input-->
<!--          v-model="queryParams.afterRemainingMileage"-->
<!--          placeholder="请输入加后剩余里程"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="优惠金额" prop="discountAmount">-->
<!--        <el-input-->
<!--          v-model="queryParams.discountAmount"-->
<!--          placeholder="请输入优惠金额"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="型号" prop="carType">
        <el-select v-model="queryParams.carType" placeholder="型号" clearable>
          <el-option
            v-for="dict in dict.type.car_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="开票状态" prop="invoiceStatus">
        <el-select v-model="queryParams.invoiceStatus" placeholder="开票状态" clearable>
          <el-option
            v-for="dict in dict.type.invoice_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="数据范围" prop="dataScope">-->
<!--        <el-input-->
<!--          v-model="queryParams.dataScope"-->
<!--          placeholder="请输入数据范围"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['trip:oil:add']"
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
          v-hasPermi="['trip:oil:edit']"
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
          v-hasPermi="['trip:oil:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['trip:oil:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="oilList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="oilId" width="100" />
      <el-table-column label="加油日期" align="center" prop="oilTime" width="130">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oilTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="金额/元" align="center" prop="amount" />
      <el-table-column label="油量/升L" align="center" prop="oilLitre" />
      <el-table-column label="油类型" align="center" prop="oilType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.oil_type" :value="scope.row.oilType"/>
        </template>
      </el-table-column>
      <el-table-column label="单价" align="center" prop="oilPrice" />
<!--      <el-table-column label="型号" align="center" prop="carType" />-->
      <el-table-column label="型号" align="center" prop="carType" width="135" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.car_type" :value="scope.row.carType"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="加前剩余里程" align="center" prop="beforeRemainingMileage" width="100" />-->
<!--      <el-table-column label="加后剩余里程" align="center" prop="afterRemainingMileage" width="100" />-->
      <el-table-column label="付款方式" align="center" prop="payType" />
<!--      <el-table-column label="开票状态" align="center" prop="invoiceStatus" />-->
      <el-table-column label="开票状态" align="center" prop="invoiceStatus" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.invoice_status" :value="scope.row.invoiceStatus"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="优惠金额" align="center" prop="discountAmount" />-->
<!--      <el-table-column label="实付金额" align="center" prop="actualPayment" />-->
<!--      <el-table-column label="数据范围" align="center" prop="dataScope" />-->
<!--      <el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['trip:oil:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['trip:oil:remove']"
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

    <!-- 添加或修改行驶加油信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="加油日期" prop="oilTime">
          <el-date-picker clearable
            v-model="form.oilTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择加油日期">
          </el-date-picker>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="金额/元" prop="amount">
              <el-input v-model="form.amount" placeholder="请输入金额/元" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="油量/升L" prop="oilLitre">
              <el-input v-model="form.oilLitre" placeholder="请输入油量/升L" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="单价" prop="oilPrice">
              <el-input v-model="form.oilPrice" placeholder="请输入单价" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="油类型" prop="oilType" >
              <el-select v-model="form.oilType" placeholder="请选择型号">
                <el-option
                  v-for="dict in dict.type.oil_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="型号" prop="carType">
          <el-select v-model="form.carType" placeholder="请选择型号">
            <el-option
              v-for="dict in dict.type.car_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="付款方式" prop="payType">
          <el-input v-model="form.payType" placeholder="请输入付款方式" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="加前剩余里程" prop="beforeRemainingMileage">
              <el-input v-model="form.beforeRemainingMileage" placeholder="请输入加前剩余里程" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="加后剩余里程" prop="afterRemainingMileage">
              <el-input v-model="form.afterRemainingMileage" placeholder="请输入加后剩余里程" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="优惠金额" prop="discountAmount">
              <el-input v-model="form.discountAmount" placeholder="请输入优惠金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="实付金额" prop="actualPayment">
              <el-input v-model="form.actualPayment" placeholder="请输入实付金额" />
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
import { listOil, getOil, delOil, addOil, updateOil } from "@/api/trip/oil";

export default {
  name: "Oil",
  dicts: ['car_type','invoice_status','oil_type'],
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
      // 行驶加油信息表格数据
      oilList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        oilTime: null,
        amount: null,
        oilLitre: null,
        oilPrice: null,
        carType: null,
        beforeRemainingMileage: null,
        afterRemainingMileage: null,
        payType: null,

discountAmount: null,

actualPayment: null,
        dataScope: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        oilTime: [
          { required: true, message: "加油日期不能为空", trigger: "change" }
        ],
        amount: [
          { required: true, message: "金额/元不能为空", trigger: "blur" }
        ],
        oilLitre: [
          { required: true, message: "油量/升L不能为空", trigger: "blur" }
        ],
        oilPrice: [
          { required: true, message: "单价不能为空", trigger: "blur" }
        ],
        oilType: [
          { required: true, message: "油类型不能为空", trigger: "change" }
        ],
        carType: [
          { required: true, message: "型号不能为空", trigger: "change" }
        ],
        // beforeRemainingMileage: [
        //   { required: true, message: "加前剩余里程不能为空", trigger: "blur" }
        // ],
        // afterRemainingMileage: [
        //   { required: true, message: "加后剩余里程不能为空", trigger: "blur" }
        // ],
        payType: [
          { required: true, message: "付款方式不能为空", trigger: "change" }
        ],
        // discountAmount: [
        //   { required: true, message: "优惠金额不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询行驶加油信息列表 */
    getList() {
      this.loading = true;
      listOil(this.queryParams).then(response => {
        this.oilList = response.rows;
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
        oilId: null,
        oilTime: null,
        amount: null,
        oilLitre: null,
        oilPrice: null,
        carType: null,
        beforeRemainingMileage: null,
        afterRemainingMileage: null,
        payType: null,
        discountAmount: null,
        actualPayment: null,
        delFlag: null,
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
      this.ids = selection.map(item => item.oilId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加行驶加油信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const oilId = row.oilId || this.ids
      getOil(oilId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改行驶加油信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.oilId != null) {
            updateOil(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOil(this.form).then(response => {
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
      const oilIds = row.oilId || this.ids;
      this.$modal.confirm('是否确认删除行驶加油信息编号为"' + oilIds + '"的数据项？').then(function() {
        return delOil(oilIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('trip/oil/export', {
        ...this.queryParams
      }, `oil_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
