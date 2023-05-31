<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="20">
        <el-col :span="24">
          <el-row>
            <el-col :span="2" style=";margin-top:.5rem;">
              <span style="font-size:.9rem">Order Status：</span>
            </el-col>
            <el-col :span="22">
              <el-button :type="query.button.css.status.all" size="small" @click.native="queryByState('all')" >All</el-button>
              <el-button  size="mini" :type="query.button.css.status.unPay" @click.native="queryByState('unPay')">Payment pending &nbsp;<el-tag type="success"  size="mini">{{query.button.tag.unPay}}</el-tag></el-button>
              <el-button  size="mini" :type="query.button.css.status.unSend" @click.native="queryByState('unSend')">To be shipped&nbsp;<el-tag type="danger"  size="mini">{{query.button.tag.unSend}}</el-tag></el-button>
              <el-button  size="mini" :type="query.button.css.status.sended" @click.native="queryByState('sended')">Shipped&nbsp;<el-tag type="info"  size="mini">{{query.button.tag.sended}}</el-tag></el-button>
              <el-button  size="mini" :type="query.button.css.status.finished" @click.native="queryByState('finished')">Completed&nbsp;<el-tag type="info"  size="mini">{{query.button.tag.finished}}</el-tag></el-button>
              <el-button  size="mini" :type="query.button.css.status.cancel" @click.native="queryByState('cancel')">Canceled&nbsp;<el-tag type="info"  size="mini">{{query.button.tag.cancel}}</el-tag></el-button>
              <el-button  size="mini" :type="query.button.css.status.refundIng" @click.native="queryByState('refundIng')">Refund in progress&nbsp;<el-tag type="danger"  size="mini">{{query.button.tag.refundIng}}</el-tag></el-button>
              <el-button  size="mini" :type="query.button.css.status.refund" @click.native="queryByState('refund')">Refunded&nbsp;<el-tag type="info"  size="mini">{{query.button.tag.refund}}</el-tag></el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="2" style=";margin-top:.5rem;">
              <span style="font-size:.9rem;">Order Date：</span>
            </el-col>
            <el-col :span="22">
              <el-button size="small" :type="query.button.css.date.all"  @click.native="queryByDate('all')">All</el-button>
              <el-button  size="small" :type="query.button.css.date.today"  @click.native="queryByDate('today')">Today</el-button>
              <el-button  size="small" :type="query.button.css.date.yesterday"  @click.native="queryByDate('yesterday')">Yesterday</el-button>
              <el-button  size="small" :type="query.button.css.date.seven"  @click.native="queryByDate('seven')">Last 7 days</el-button>
              <el-button  size="small" :type="query.button.css.date.thirty"  @click.native="queryByDate('thirty')">Last 30 days</el-button>
              <el-button  size="small" :type="query.button.css.date.month"  @click.native="queryByDate('month')">This month</el-button>
              <el-button  size="small" :type="query.button.css.date.year"  @click.native="queryByDate('year')">This year</el-button>
              <el-button  size="small" :type="query.button.css.date.customer"  @click.native="queryByDate('customer')">Customization</el-button>
              <el-date-picker
                v-show="query.button.showCustomer"
                v-model="orderDate"
                size="mini"
                type="daterange"
                range-separator="to"
                value-format="yyyy-MM-dd"
                start-placeholder="Start Date"
                end-placeholder="End date">
              </el-date-picker>
            </el-col>
          </el-row>
          <br>
        </el-col>
        <el-col :span="4">
          <el-input v-model="listQuery.mobile" size="mini" placeholder="Phone number"></el-input>
        </el-col>
        <el-col :span="4">
          <el-input v-model="listQuery.orderSn" size="mini" placeholder="Order Number"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-document" @click.native="exportXls">{{ $t('button.export') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">
      <el-table-column label="User">
        <template slot-scope="scope">
          <router-link :to="{path:'shopUserDetail?id='+scope.row.user.id}">
            {{scope.row.user.mobile}}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="Order Number">
        <template slot-scope="scope">
          <router-link :to="{path:'orderDetail?orderSn='+scope.row.orderSn}">
            {{scope.row.orderSn}}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="Creation Date">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column label="Order Status">
        <template slot-scope="scope">
          {{scope.row.statusName}}
        </template>
      </el-table-column>
      <el-table-column label="Payment Status">
        <template slot-scope="scope">
          {{scope.row.payStatusName}}
        </template>
      </el-table-column>
      <el-table-column label="Payment Method">
        <template slot-scope="scope">
          {{scope.row.payTypeName}}
        </template>
      </el-table-column>
      <el-table-column label="Order Remarks">
        <template slot-scope="scope">
          {{scope.row.message}}
        </template>
      </el-table-column>
      <el-table-column label="Total amount">
        <template slot-scope="scope">
          {{formatPrice(scope.row.totalPrice)}}
        </template>
      </el-table-column>
      <el-table-column label="Operation">
        <template slot-scope="scope">
          <el-dropdown  size="small"   split-button type="primary">

              Operation
            <el-dropdown-menu slot="dropdown">
              <!--<el-dropdown-item v-if="scope.row.statusName === 'Payment pending'">修改订单</el-dropdown-item>-->
              <el-dropdown-item @click.native="addComment(scope.row.id)">Order Remarks</el-dropdown-item>
              <el-dropdown-item v-if="scope.row.statusName==='Shipped'" @click.native="viewShippingInfo(scope.row)">Logistics Information</el-dropdown-item>
              <el-dropdown-item v-if="scope.row.statusName === 'To be shipped'" @click.native="openSendOutForm(scope.row.id)">Ship Now</el-dropdown-item>
              <el-dropdown-item v-if="scope.row.payStatusName === 'Paid' && scope.row.statusName !== 'Refunded'&& scope.row.statusName !== 'Refund in progress'">Get a refund now</el-dropdown-item>
              <el-dropdown-item @click.native="viewLog(scope.row.id)">Operation Log</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.limit"
      :total="total"
      :current-page.sync="listQuery.page"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext">
    </el-pagination>
    <el-dialog
      title="Order Log"
      :visible.sync="logVisible"
      width="40%">
      <el-table
        :data="logs"
        stripe
        border
        style="width: 100%">
        <el-table-column
          prop="descript"
          label="Operation log">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="Operation time">
        </el-table-column>
      </el-table>
    </el-dialog>
    <el-dialog
      title="Shipping"
      :visible.sync="shipping.show"
      width="40%">
      <el-form ref="form" :model="shipping"  label-width="200px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="Courier Company"  >
              <el-select v-model="shipping.idExpress" placeholder="choose">
                <el-option
                  v-for="item in expressList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="Courier Bill Number"  >
              <el-input v-model="shipping.shippingSn" minlength=1></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="sendOut">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="shipping.show = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
    <el-dialog
      title="Logistics Information"
      :visible.sync="shippingInfo.show"
      width="80%">
      <el-form ref="form"   label-width="120px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="Courier Company"  >
              <el-select v-model="shippingInfo.form.idExpress"   disabled>
                <el-option
                  v-for="item in expressList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="Courier Bill Number"  >
              <el-input v-model="shippingInfo.form.shippingSn"  readOnly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="Logistics progress">
              <br>
              <el-timeline :reverse="true">
                <el-timeline-item
                  v-for="(activity, index) in shippingInfo.form.traces"
                  :key="index"
                  :timestamp="activity.AcceptTime">
                  {{activity.AcceptStation}}
                </el-timeline-item>
              </el-timeline>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>

          <el-button @click.native="shippingInfo.show = false">{{ $t('button.close') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

  </div>
</template>

<script src="./order.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

