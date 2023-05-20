<template>
    <div>
        <van-cell-group>
        <van-cell :title="addrText" is-link to="address"/>
        </van-cell-group>
            <div   v-for="item in cartList"
                   :key="item.id"    class="card-goods__item">
                <van-card
                        style="margin-left:15px;"
                        :title="item.title"
                        :desc="item.goods.descript"
                        :num="item.count"
                        :price="formatPrice(item.price)"
                        :thumb="item.thumb"
                >
                </van-card>
            </div>

        <van-submit-bar
                :price="totalPrice"
                :disabled="!checkedGoods.length"
                :button-text="submitBarText"
                @submit="submit"
        >

        </van-submit-bar>

        <van-tabbar v-model="activeFooter">
            <van-tabbar-item icon="home-o"  replace to="/index">Home</van-tabbar-item>
            <van-tabbar-item icon="search"  replace to="/search">Found</van-tabbar-item>
            <!--            <van-tabbar-item icon="home-o" replace to="">Upload</van-tabbar-item>-->
            <van-tabbar-item icon="cart-o"  replace to="/cart">Shopping Cart</van-tabbar-item>
            <van-tabbar-item icon="user-o"  replace to="/user">Me</van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script src="./checkout.js"></script>

<style lang="less">
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
