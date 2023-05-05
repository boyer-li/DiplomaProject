<template>
  <div>
    <van-nav-bar
            title="My Orders"
            left-arrow
            @click-left="onClickLeft"
    />
    <van-tabs v-model="activeNav" @click="clickNav">
      <van-tab v-for="nav in navList" :title="nav.name" v-bind:key="nav.id">
      </van-tab>
    </van-tabs>
    <div class="order_list">
    <van-list v-model="loading"
              :finished="finished"
              :immediate-check="false"
              finished-text="No more"
              @load="getData">
      <van-panel v-for="(item, index) in orderList"
                 :key="index"
                 :title="'Order Number: ' + item.orderSn"
                 :status="item.statusName"
                 @click.native="toOrderDetail(item.orderSn)"
      >
        <van-card v-for="(orderItem, index2) in item.items"
                  :key="index2"
                  :title="orderItem.title"
                  :desc="orderItem.goods.descript"
                  :price="formatPrice(orderItem.price)"
                  :num="orderItem.count"
                  @click.stop="toGoods(orderItem.goods.id)"
                  :thumb="imgUrl+orderItem.goods.pic">
          <!--<div slot="desc">-->
            <!--<div class="desc">-->
              <!--<van-tag plain-->
                       <!--style="margin-right:6px;"-->
                       <!--v-for="(spec, index) in goods.specifications"-->
                       <!--:key="index">-->
                <!--{{spec}}-->
              <!--</van-tag>-->
            <!--</div>-->
          <!--</div>-->
        </van-card>
        <div class="total">Total: {{formatPrice(item.totalPrice)}} </div>

        <div slot="footer"
             class="footer_btn">
          <van-button size="small" @click.stop="cancelOrder(item)"  v-show="item.statusName === 'Payment pending'" type="default">Cancel order</van-button>
          <van-button size="small" @click.stop="viewExpressInfo(item.orderSn)"  v-show="item.statusName === 'Shipped' || item.statusName === 'Completed'" type="info">View Logistics</van-button>

          <van-button size="small" @click.stop="handleOrder(item)"  type="danger">
            {{getHandlerText(item.statusName)}}
          </van-button>

        </div>

      </van-panel>

    </van-list>
    </div>



        <van-tabbar v-model="activeFooter">
            <van-tabbar-item icon="home-o"  replace to="/index">Home</van-tabbar-item>
            <van-tabbar-item icon="search"  replace to="/search">Found</van-tabbar-item>
            <van-tabbar-item icon="home-o" replace to="">Upload</van-tabbar-item>
            <van-tabbar-item icon="cart-o"  replace to="/cart">Shopping Cart</van-tabbar-item>
            <van-tabbar-item icon="user-o"  replace to="/user">Me</van-tabbar-item>
        </van-tabbar>
  </div>
</template>

<script src="./orders.js"></script>

<style lang="less">
  .order_list {
    .van-panel {
      margin-top: 5px;
    }

    .van-card {
      background-color: #fff;
    }

    .total {
      text-align: right;
      padding: 10px;
    }

    .footer_btn {
      text-align: right;
      .van-button {
        margin-left: 10px;
      }
    }
  }
</style>
