<template>
    <div>
        <van-nav-bar
                title="Order details"
                left-arrow
                @click-left="onClickLeft"
        />
        <div class="order_detail">
            <van-panel :title="order.consignee +' '+  order.mobile" :status="order.statusName">
                <div class="address_detail">
                    {{order.consigneeAddress}}
                    <br>
                </div>
                <div slot="footer"
                     class="footer_btn">
                    <van-button size="small" type="default" @click="contact">Contact Customer Service</van-button>
                    <van-button size="small" type="danger" @click="payment" v-show="order.statusName=='Payment pending'">Pay Now</van-button>
                    <van-button size="small" type="danger" @click="confirmReceive" v-show="order.statusName=='Shipped'">Confirm receipt</van-button>
                </div>
            </van-panel>
            <van-panel :title="'Order Number: ' + order.orderSn">
                <van-card v-for="(orderItem, index) in order.items"
                          :key="index"
                          :title="orderItem.goods.name"
                          :desc="orderItem.goods.descript"
                          :price="formatPrice(orderItem.price)"
                          :num="orderItem.count"
                          @click="toGoods(orderItem.goods.id)"
                          :thumb="imgUrl+orderItem.goods.pic">

                </van-card>
                <div class="total">Total: {{formatPrice(order.totalPrice)}}</div>

            </van-panel>
            <van-panel title="Order Information" >
                <van-cell-group>
                    <van-cell title="Order Number" :value="order.orderSn" />
                    <van-cell title="Remarks" :value="order.message"   />
                    <van-cell title="Creation time" :value="order.createTime"/>
                </van-cell-group>
            </van-panel>
            <br><br><br>
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

<script src="./detail.js"></script>

<style lang="less">
    .order_detail {
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
        .address_detail{
            padding: 5px;
        }
    }
</style>
