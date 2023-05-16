<template>
    <div>
        <van-nav-bar
                title="Payment Results"
        />
        <div class="result">
        <van-icon v-if="isSuccess" name="success" size="80" color="green"/>

        <van-icon v-if="!isSuccess" name="cross" size="80" color="red"/><br>

       <span>{{result}}</span>


            <br> <br>
            <div v-if="result!=='Inquiries'">
                <van-button type="primary"  @click="toOrder">View Orders</van-button>
                <van-button type="info"  @click="toIndex">Continue Shopping</van-button>
            </div>
        </div>
    </div>
</template>

<script src="./callback.js"></script>

<style lang="less">
    .result {
        margin-top:10%;
        text-align: center;
    }


</style>
