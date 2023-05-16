<template>
    <div class="updateUserName">
        <van-nav-bar
                title="Change password"
                left-arrow
                @click-left="onClickLeft"
        />

        <van-cell-group>
            <van-field
                    v-model="oldPwd"
                    required
                    clearable
                    label="Old Password"
                    placeholder="Please enter"
            />
            <van-field
                    v-model="password"
                    required
                    clearable
                    label="New Password"
                    placeholder="Please enter"
            />
            <van-field
                    v-model="rePassword"
                    required
                    clearable
                    label="Confirm Password"
                    placeholder="Please enter"
            />
        </van-cell-group>
            <van-button type="primary"   round block @click="submit">Submit</van-button>

    </div>
</template>

<script src="./updatePwd.js"></script>

<style lang="less">
    .updateUserName {
        &-group {
            margin-bottom: 15px;
        }
    }
</style>
