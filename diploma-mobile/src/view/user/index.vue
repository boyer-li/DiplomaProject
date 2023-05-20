<template>
  <div>
    <img class="user-poster" src="@/assets/img/banner.jpg" >
    <van-row class="user-links">
      <van-col span="6">
        <van-icon name="pending-payment" @click="toOrder(1)"/>
        Payment pending
      </van-col>
      <van-col span="6">
        <van-icon name="tosend" @click="toOrder(2)"/>
        To be shipped
      </van-col>
      <van-col span="6">
        <van-icon name="logistics" @click="toOrder(3)"/>
        Shipped
      </van-col>
      <van-col span="6">
        <van-icon name="bag-o" @click="toOrder(4)"/>
        Completed
      </van-col>
    </van-row>

    <van-cell-group class="user-group">
      <van-cell icon="records" title="Orders" is-link to="order" />
    </van-cell-group>

    <van-cell-group>
      <van-cell icon="location-o" title="Address" is-link to="address"/>
      <van-cell icon="star-o" title="Favorite" is-link to="favorite"/>
      <van-cell icon="points" title="Point" is-link @click="sorry"/>
      <van-cell icon="gold-coin-o" title="Coupons" is-link @click="sorry" />
      <van-cell icon="gift-o" title="Gift" is-link  @click="sorry"/>
      <van-cell icon="setting-o" title="Setting" is-link to="setting"/>
    </van-cell-group>
    <br><br>
        <van-tabbar v-model="activeFooter">
            <van-tabbar-item icon="home-o"  replace to="/index">Home</van-tabbar-item>
            <van-tabbar-item icon="search"  replace to="/search">Found</van-tabbar-item>
            <!--            <van-tabbar-item icon="home-o" replace to="">Upload</van-tabbar-item>-->
            <van-tabbar-item icon="cart-o"  replace to="/cart">Shopping Cart</van-tabbar-item>
            <van-tabbar-item icon="user-o"  replace to="/user">Me</van-tabbar-item>
        </van-tabbar>

  </div>
</template>

<script src="./user.js"></script>

<style lang="less">
.user {
  &-poster {
    width: 100%;
    display: block;
  }

  &-group {
    margin-bottom: 15px;
  }

  &-links {
    padding: 15px 0;
    font-size: 12px;
    text-align: center;
    background-color: #fff;

    .van-icon {
      display: block;
      font-size: 24px;
    }
  }
}
</style>
