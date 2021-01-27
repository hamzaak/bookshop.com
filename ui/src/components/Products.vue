<template>
    <div>
        <Navbar />
        <div class="page-content">
            <h2>Products</h2>
            <table class="table" v-if="products && products.length">
            <thead>
                <tr>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="p of products" :key="p.id">
                <td>{{p.title}}</td>
                <td>{{p.description}}</td>
                <td align="right">
                    <button class="btn btn-danger" type="button" @click="buy(p)">
                        Buy ${{formatPrice(p.price)}}
                    </button>
                </td>
                </tr>
            </tbody>
            </table>
        </div>
    </div>
  
</template>

<script>

import axios from 'axios';
import Navbar from './Navbar.vue'
import { API_PRODUCTS_URL, API_CART_URL } from "@/constants/config";

export default {
  name: "Products",
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
        this.$router.push({path: 'signIn'});
    }

    axios.get(API_PRODUCTS_URL + `/get/all`)
        .then(response => {
        this.products = response.data
        })
        .catch(e => {
        this.errors.push(e)
        })
  },
  methods: {
    formatPrice(value) {
        let val = (value/1).toFixed(2).replace('.', ',')
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
    },
    buy(product) {
        var shoppingCartItem = {
            accountId: this.accountId,
            productId: product.id,
            quantity: 1 };

        axios.post(API_CART_URL + `/add`, shoppingCartItem)
            .then(response => {
                console.log(response);
                this.$store.commit('updateCart', response.data);
            })
            .catch(e => {
                console.log(e);
            })
    }
}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .page-content {
        padding: 40px;
    }
</style>
