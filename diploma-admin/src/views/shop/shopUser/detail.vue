<template>
  <div class="app-container">


    <el-form ref="form"   label-width="120px" label-position="left">
      <el-row>
        <h3>Basic Information</h3>

        <el-col :span="12">
          <el-form-item label="Avatar">
            <el-avatar   :src="avatarUrl" ></el-avatar>

          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Nickname">
            <span>{{userInfo.info.nickName}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Phone number">
            <span>{{userInfo.info.mobile}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Registration Time">
            <span>{{userInfo.info.createTime}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Last login time">
           <span>{{userInfo.info.lastLoginTime}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Order volume">
            <span>{{userInfo.orderCount}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Number of carts">
            <span>{{userInfo.cartCount}}</span>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <h3>Receiving Address</h3>
        <p v-for="item in addressList" :key="item.id">
          {{item.name}}&nbsp;&nbsp; {{item.tel}}&nbsp;&nbsp;{{item.province}}{{item.city}}{{item.district}}{{item.addressDetail}}
        </p>
      </el-row>

    </el-form>
  </div>
</template>

<script src="./shopUserDetail.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

