<template>
  <div class="app-container">
    <div class="block">
      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-edit" @click.native="openSendOutForm"
                     v-show="form.statusName =='To be shipped'">Shipping
          </el-button>
          <el-button type="default" size="mini" @click="printOrder">Print</el-button>
        </el-col>
      </el-row>
    </div>

    <el-form ref="print"   label-width="150px" >
      <el-row>
        <h3>Basic Information</h3>
        <el-col :span="12">
          <el-form-item label="Order Number">
            <span> {{ form.orderSn}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Order Users">
            <router-link :to="{path:'shopUserDetail?id='+form.user.id}">
              {{ form.user.mobile}}({{form.user.nickName}})
            </router-link>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Status">
            <span> <strong>{{ form.statusName }} </strong></span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Order Remarks">
            <span>{{ form.message }} </span>
          </el-form-item>
        </el-col>
        <h3>Receiving Information</h3>
        <el-col :span="12">
          <el-form-item label="Consignee">
            <span>{{ form.consignee}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Contact number">
            <span>{{ form.mobile}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Receiving Address">
            <span>{{ form.consigneeAddress}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <h3>Order details</h3>
        </el-col>
        <el-table :data="form.items" border fit  >
          <el-table-column label="Used goods">
            <template slot-scope="scope">
              <router-link :to="{path:'goodsEdit?id='+scope.row.goods.id}">
                {{scope.row.title}}
              </router-link>
            </template>
          </el-table-column>
          <el-table-column label="Pictures">
            <template slot-scope="scope">
            <img :src="apiUrl+ '/file/getImgStream?idFile=' + scope.row.goods.pic" style="width:50px;">
            </template>
          </el-table-column>
          <el-table-column label="Price">
            <template slot-scope="scope">
              {{formatPrice(scope.row.price)}}
            </template>
          </el-table-column>
          <el-table-column label="Quantity">
            <template slot-scope="scope">
              {{scope.row.count}}
            </template>
          </el-table-column>
          <el-table-column label="Total">
            <template slot-scope="scope">
              {{ formatPrice(scope.row.totalPrice)}}
            </template>
          </el-table-column>
          <el-table-column label="" >
            <!--todo 打印的时候总是无法打印表格最后一列，此列用于占位-->
          </el-table-column>

        </el-table>
        <el-col :span="8">
          <el-form-item label="Total amount">
            <span>{{formatPrice(form.totalPrice)}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Coupon Credit Amount">
            <span> {{formatPrice(form.couponPrice)}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Actual amount paid">
            <span>{{formatPrice(form.realPrice)}}</span>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
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

  </div>
</template>

<script src="./orderDetail.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

