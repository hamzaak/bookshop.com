<template>
    <div>
        <Navbar />
        <div class="page-content">
            <h2>Product List</h2>
            <div class="row" v-if="products && products.length">
                <div class="col-sm-6" v-for="p of products" :key="p.id">
                    <div class="card">
                        <img class="card-img-top" height="300" v-bind:src=formatPicUrl(p.picUrl) v-bind:alt=p.title>
                        <div class="card-body">
                            <h5 class="card-title">{{ p.title }}</h5>
                            <p class="card-text">{{p.description}}</p>
                            <button class="btn btn-danger" type="button" @click="buy(p)">
                                Buy ${{formatPrice(p.price)}}
                            </button>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
    </div>
  
</template>

<script>

import Navbar from '@/components/Navbar.vue'
import productClient from '@/api/productClient'
import cartClient from '@/api/cartClient'
import { BASE_IP } from '@/api/config'

export default {
  name: 'ProductList',
  components: {
    Navbar
  },
  data() {
    return {
      products: [],
      accountId: localStorage.getItem('accountId')
    }
  },
  created() {
    if(this.accountId == null) {
        this.$router.push({path: 'login'})
    }

    productClient.getAll()
        .then(data => {
            this.products = data
        })
        .catch(e => {
            console.log(e)
        })
  },
  methods: {
    formatPicUrl(relativeUrl) {
        return 'http://' + BASE_IP + ':9000/' + relativeUrl
    },
    formatPrice(value) {
        let val = (value/1).toFixed(2).replace('.', ',')
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
    },
    buy(product) {
        var shoppingCartItem = {
            accountId: this.accountId,
            productId: product.id,
            quantity: 1 }

        cartClient.add(shoppingCartItem)
            .then(data => {
                console.log(JSON.stringify(data))
                this.$store.commit('updateCart', data)
            })
            .catch(e => {
                console.log(e)
            })
    }
}
};
</script>

<style scoped>
    .page-content {
        padding: 40px;
    }
    .card {
        flex-direction: row;
        margin-top:20px;
    }
    .card-body {
        padding: 1rem 1rem 0rem 1rem;
    }
    .card-text {
        display: -webkit-box;
        -webkit-line-clamp: 5;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
    }
</style>
