<template>
    <div class="updateMobile">
        <van-nav-bar
                title="更换手机"
                left-arrow
                @click-left="onClickLeft"
        />
        <van-row>
            <van-col span="24">
                <strong>To confirm your identity, we still need to verify your secure cell phone</strong>
                <p>Click the button and a verification code will be sent<span style="color: #FF0000;">{{oldMobile}}</span></p>
            </van-col>
        </van-row>

        <van-cell-group v-show="showOldMobileSmsCode">
            <van-field
                    v-model="oldMobileSmsCode"
                    center
                    clearable
                    label="SMS verification code"
                    placeholder="Please enter the SMS verification code"
            >
                <van-button slot="button" size="small" type="primary" @click="sendSmsCode" v-show="!hasSendSms">Send verification code</van-button>
                <van-button slot="button" size="small" disabled type="primary" v-show="hasSendSms">After {{second}} seconds, reacquire</van-button>

            </van-field>
        </van-cell-group>
            <van-button type="warning"  v-show="showSendBtn"   block @click="sendSmsCode">Send SMS</van-button>
        <van-button type="primary"     v-show="showConfirmBtn" block @click="confirmOldMobileSmsCode">Submit</van-button>

    </div>
</template>

<script src="./updateMobile.js"></script>

<style lang="less">
    .updateMobile {
        .van-row{
            padding:16px;
        }
        .van-col{
            background-color: #f8f8f8;
        }
    }
</style>
