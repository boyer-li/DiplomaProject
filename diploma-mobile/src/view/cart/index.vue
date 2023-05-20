<template>
  <div class="cart">
    <van-nav-bar
            title="Shopping Cart"
            :right-text="rightText"
            @click-right="onClickRight"
    />
    <van-checkbox-group class="card-goods" v-model="checkedCartItem" v-if="isLogin && cartList.length>0">
      <div   v-for="item in cartList"
             :key="item.id"    class="card-goods__item">
      <van-checkbox
        :name="item.id"
      ></van-checkbox>
        <van-card
                style="margin-left:15px;"
          :title="item.title"
          :desc="item.goods.descript"

          :price="formatPrice(item.price)"
          :thumb="item.thumb"
        >

          <div slot="footer">
            <van-stepper v-model="item.count" @change="stepperEvent(item,arguments)" disableInput/>
          </div>
        </van-card>
      </div>
      <br><br><br><br><br>

    </van-checkbox-group>
    <van-submit-bar
            v-show="!showEdit"
      :price="totalPrice"
      :disabled="!checkedCartItem.length"
      :button-text="submitBarText"
      @submit="submit"
      v-if="isLogin && cartList.length>0"
    >
      <van-checkbox v-model="checkedAll" @click="checkAll" style="padding: 0 10px;">Select All</van-checkbox>
    </van-submit-bar>
      <van-submit-bar
              v-show="showEdit"
              :price="totalPrice"
              :disabled="!checkedCartItem.length"
              button-text="Delete"
              @submit="remove"
              v-if="isLogin && cartList.length>0"
      >
      <van-checkbox v-model="checkedAll" @click="checkAll" style="padding: 0 10px;">Select All</van-checkbox>
    </van-submit-bar>
    <div class="no-data" v-if="isLogin && cartList.length ===0 ">
      <p style="text-align: center;color:lightgray">
        <van-icon name="cart-o" style="text-align: center" size="3rem"/><br>
        Shopping cart is still empty</p>
      <van-button type="default" block round @click="toHome">Go shopping</van-button>
    </div>
    <div class="no-data" v-if="!isLogin" >
      <p style="text-align: center;color:lightgray">
        <van-icon name="cart-o" style="text-align: center" size="3rem"/><br>
        Not logged in yet</p>
      <van-button type="primary" block round @click="toLogin">Login Now</van-button>
    </div>

        <van-tabbar v-model="activeFooter">
            <van-tabbar-item icon="home-o"  replace to="/index">Home</van-tabbar-item>
            <van-tabbar-item icon="search"  replace to="/search">Found</van-tabbar-item>
            <!--            <van-tabbar-item icon="home-o" replace to="">Upload</van-tabbar-item>-->
            <van-tabbar-item icon="cart-o"  replace to="/cart">Shopping Cart</van-tabbar-item>
            <van-tabbar-item icon="user-o"  replace to="/user">Me</van-tabbar-item>
        </van-tabbar>
  </div>
</template>

<script src="./cart.js"></script>

<style lang="less">
  .cart{
    .no-data{
      margin-top:60%;
    }
  }
  .van-submit-bar{
   bottom:48px;
  }
.card-goods {
  background-color: #fff;

  &__item {
    position: relative;
    background-color: #fafafa;

    .van-checkbox__label {
      width: 100%;
      height: auto; // temp
      padding: 0 10px 0 15px;
      box-sizing: border-box;
    }

    .van-checkbox__icon {
      top: 50%;
      left: 10px;
      z-index: 1;
      position: absolute;
      margin-top: -10px;
    }

    .van-card__price {
      color: #f44;
    }
  }
}
</style>
