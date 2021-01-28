import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import { store } from './store/store'
import Login from './views/Login.vue'
import ProductList from './views/ProductList.vue'
import Cart from './views/Cart.vue'


Vue.config.productionTip = false
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', component: Login },
    { path: '/login', component: Login },
    { path: '/products', component: ProductList },
    { path: '/cart', component: Cart }
  ]
})

new Vue({
  render: h => h(App),
  store: store,
  router: router
}).$mount('#app')
