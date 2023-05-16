<template>
    <div class="setting">
        <van-nav-bar
                title="Setting"
                left-arrow
                @click-left="onClickLeft"
        />
        <van-cell-group>
            <van-cell icon="flower-o" title="Avatar" is-link to="avatar">
                <van-image
                        round
                        width="30"
                        height="30"
                        :src="avatarUrl"
                />
            </van-cell>
            <van-cell icon="user-o" title="Name" :value="user.nickName"is-link to="updateUserName"/>
            <van-cell icon="share" title="Gender" is-link :value="user.genderStr" @click="showGender=true"/>
            <van-cell icon="closed-eye" title="Change password" is-link to="updatePwd"/>
            <van-cell icon="phone-o" title="Change cell phone number" is-link to="updateMobile"/>
        </van-cell-group>
        <div class="logout">
        <van-button type="danger" plain round block @click="onLogout">Sign out</van-button>
        </div>
        <van-popup v-model="showGender"  :style="{ padding:'20px',width:'50px', height: '50px' }">
            <van-radio-group v-model="user.gender" @change="onChangeGender">
                <van-radio name="male">Male</van-radio>
                <van-radio name="female">Female</van-radio>
            </van-radio-group>
        </van-popup>
    </div>
</template>

<script src="./setting.js"></script>

<style lang="less">
    .setting {
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
        .logout{
            position:fixed;
            bottom:0px;
            width:100%;
        }
    }
</style>
