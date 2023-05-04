import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
import storage from '@/utils/storage'
Vue.use(Router)

const routes = [
    {
        path: '*',
        redirect: '/index'
    },
    {
        name: 'error',
        component: () => import('./view/common/error'),
        meta: {
            requireAuth: true,
            title: 'System anomaly'
        }
    },
    {
        name: 'login',
        component: () => import('./view/login'),
        meta: {
            title: 'Login'
        }
    },
    {
        name: 'index',
        component: () => import('./view/index'),
        meta: {
            title: 'Automated Second-Hand Trading Company Information System'
        }
    },
    {
        path: '/topic/:id',
        name: 'topic',
        component: () => import('./view/topic'),
        meta: {
            title: 'Recommended Topics'
        }
    },
    {
        name: 'list',
        component: () => import('./view/goods/list'),
        meta: {
            title: 'Automated Second-Hand Trading Company Information System'
        }
    },
    {
        path: '/goods/:id',
        name: 'goods',
        component: () => import('./view/goods'),
        meta: {
            title: 'Product Details'
        }
    },
    {
        name: 'search',
        component: () => import('./view/search'),
        meta: {
            title: 'Found'
        }
    },
    {
        name: 'user',
        component: () => import('./view/user'),
        meta: {
            requireAuth: true,
            title: 'Member Center'
        }
    },
    {
        name: 'setting',
        component: () => import('./view/setting'),
        meta: {
            requireAuth: true,
            title: 'Settings'
        }
    },
    {
        name: 'updateUserName',
        component: () => import('./view/setting/updateUserName'),
        meta: {
            requireAuth: true,
            title: 'Change Name'
        }
    },
    {
        name: 'updateMobile',
        component: () => import('./view/setting/updateMobile'),
        meta: {
            requireAuth: true,
            title: 'Change phone number'
        }
    },
    {
        name: 'updatePwd',
        component: () => import('./view/setting/updatePwd'),
        meta: {
            requireAuth: true,
            title: 'Change password'
        }
    },
    {
        name: 'avatar',
        component: () => import('./view/setting/avatar'),
        meta: {
            requireAuth: true,
            title: 'Modify avatar'
        }
    },
    {
        name: 'order',
        component: () => import('./view/order'),
        meta: {
            requireAuth: true,
            title: 'My Orders'
        }
    },
    {
        name: 'order/detail/:orderSn',
        component: () => import('./view/order/detail'),
        meta: {
            requireAuth: true,
            title: 'My Orders'
        }
    },
    {
        name: 'order/expressInfo/:orderSn',
        component: () => import('./view/order/expressInfo'),
        meta: {
            requireAuth: true,
            title: 'Logistics Information'
        }
    },
    {
        name: 'favorite',
        component: () => import('./view/favorite'),
        meta: {
            requireAuth: true,
            title: 'Favorite Products'
        }
    },
    {
        name: 'payment/callback/:orderSn',
        component: () => import('./view/order/payment/callback'),
        meta: {
            requireAuth: true,
            title: 'Payment Results'
        }
    },
    {
        name: 'checkout',
        component: () => import('./view/order/checkout'),
        meta: {
            requireAuth: true,
            title: 'Submit an order'
        }
    },
    {
        name: 'payment',
        component: () => import('./view/order/payment'),
        meta: {
            requireAuth: true,
            title: 'Checkout counter'
        }
    },
    {
        name: 'address',
        component: () => import('./view/address'),
        meta: {
            title: 'Receiving Address'
        }
    },
    {
        name: 'address/edit',
        component: () => import('./view/address/edit'),
        meta: {
            title: 'Add shipping address'
        }
    },
    {
        name: 'cart',
        component: () => import('./view/cart'),
        meta: {
            // requireAuth: true,
            title: 'Shopping Cart'
        }
    }
]

routes.forEach(route => {
    route.path = route.path || '/' + (route.name || '');
})

const router = new Router({routes})

router.beforeEach((to, from, next) => {
    const title = to.meta && to.meta.title
    if (title) {
        document.title = title;
    }
    // 判断该路由是否需要登录权限
    if (to.meta.requireAuth) {
        if(storage.getToken()){
            next();
        } else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
            })
        }
    } else {
        next()
    }
})


export {
    router
}
