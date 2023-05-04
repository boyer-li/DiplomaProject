<template>
    <div class="updateUserName">
        <van-nav-bar
                title="Change Name"
                left-arrow
                @click-left="onClickLeft"
        />

        <van-cell-group>
            <van-field
                    v-model="userName"
                    required
                    clearable
                    label="User Name"
                    placeholder="Please enter"
            />
        </van-cell-group>
            <van-button type="primary"   round block @click="submit">Submit</van-button>

    </div>
</template>

<script src="./updateUserName.js"></script>

<style lang="less">
    .updateUserName {
        &-group {
            margin-bottom: 15px;
        }
    }
</style>
