<template>
    <div>
        <img class="user-poster" src="@/assets/img/banner.jpg" >


            <van-cell-group>
            <van-field
                    v-model="mobile"
                    required
                    clearable
                    label="Account"
                    placeholder="Please enter phone number"
            />
            </van-cell-group>
            <van-cell-group v-show="show1">
                <van-field
                        v-model="smsCode"
                        center
                        clearable
                        label="SMS verification code"
                        placeholder="Please enter the SMS verification code"
                >
                    <van-button slot="button" size="small" type="primary" @click="sendSms" v-show="!hasSendSms">Send verification code</van-button>
                    <van-button slot="button" size="small" disabled type="primary" v-show="hasSendSms">After {{second}} seconds, reacquire</van-button>
                </van-field>
            </van-cell-group>
            <van-cell-group  v-show="show2">
            <van-field
                    v-model="password"
                    type="password"
                    label="Password"
                    placeholder="Please enter your password"
                    required
            />

        </van-cell-group>
            <div class="button-group">
            <van-button type="warning" size="large"   v-show="show1" @click="loginOrRegister">Login / Register Now</van-button>

            <van-button type="info" size="large"   v-show="show1" @click="toLoginByPassword">Username Password Login</van-button>

            <van-button type="warning" size="large"  v-show="show2"  @click="loginByPass">Login</van-button>


        <van-button type="info" size="large"  v-show="show2" @click="toRegister">SMS login/Registration</van-button>
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

<script src="./login.js"></script>

<style lang="less">
    .button-group{
     margin: 15px;
        .van-button{
            margin-top: 10px;
        }
    }
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
