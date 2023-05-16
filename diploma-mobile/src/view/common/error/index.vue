<template>
    <div>
        <van-nav-bar
                title="System anomaly"
        />
        <p style="text-align: center;">
            {{message}}
        </p>

        <van-button to="index" type="primary" size="large" style="margin-top:70%">Back to home</van-button>
    </div>
</template>
<script>
    import {Button, NavBar} from 'vant'

    export default {
        components: {
            [NavBar.name]: NavBar,
            [Button.name]: Button
        },
        data() {
            return {
                message: ''
            }
        },
        mounted() {
            this.init()
        },
        methods: {
            init() {
                this.message = this.$route.query.message
                console.log('message', this.message)
            }
        }
    }
</script>
<style>

</style>
