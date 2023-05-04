<template>
    <div>
        <van-nav-bar
                title="Checkout counter"
        />
        <van-cell-group>
            <van-cell title="Order Number" :value="order.orderSn"/>
            <van-cell title="Amount Payable">
                <span class="red strong">￥{{formatPrice(order.totalPrice)}}</span>
            </van-cell>
        </van-cell-group>

        <van-radio-group v-model="payType">
            <van-cell-group>
                <van-cell clickable @click="radio = 'wxpay'">
                    <img

                            style="height:32px;width:120px;"
                            slot="icon"
                            slot-scope="props"
                            src="@/assets/img/wxpay.png"
                    >
                    <van-radio slot="right-icon" name="wxpay" style="padding-left:40%;"/>
                </van-cell>
                <van-cell clickable @click="radio = 'alipay'">
                    <img

                            style="height:32px;width:120px;"
                            slot="icon"
                            slot-scope="props"
                            src="@/assets/img/alipay.png"
                    >
                    <van-radio slot="right-icon" name="alipay"  style="padding-left:40%;"/>
                </van-cell>
            </van-cell-group>
        </van-radio-group>
        <van-button class="footer" type="primary" size="large" @click="pay">Pay Now</van-button>

    </div>
</template>

<script src="./payment.js"></script>

<style lang="less">
    .red {
        color: red;
    }

    .strong {
        font-weight: bold;
    }

    .van-cell-group {
        margin-top: 10px;
    }

    .footer {
        position: fixed;
        bottom: 0px;
    }
</style>
