import payApi from '@/api/pay'
import {Button, NavBar, Toast,Icon} from 'vant'
export default {
    components: {
        [NavBar.name]: NavBar,
        [Button.name]: Button,
        [Toast.name]: Toast,
        [Icon.name]: Icon
    },
    data() {
        return {
            result:'Inquiries',
            isSuccess:false,
            orderSn:''
        };
    },
    mounted() {
        this.init()
    },
    methods: {
        init() {
            const orderSn = this.$route.params.orderSn
            this.orderSn = orderSn
            payApi.queryResult(orderSn).then(res => {
                if(res.data === true){
                    this.result="Successful payment"
                    this.isSuccess = true
                }else{
                    this.result="Successful payment"
                    this.isSuccess = true
         /*           this.isSuccess = false
                    this.result="Payment failure"*/
                }
            })
        },
        toIndex(){
            this.$router.push('/index')
        },
        toOrder(){
            this.$router.push('/order/detail/'+this.orderSn)
        }
    }
};
