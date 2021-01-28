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
                <td><strong>{{p.title}}</strong></td>
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

import Navbar from '@/components/Navbar.vue'
import productClient from "@/api/productClient";
import cartClient from "@/api/cartClient";

export default {
  name: "ProductList",
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
        this.$router.push({path: 'login'});
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
    formatPrice(value) {
        let val = (value/1).toFixed(2).replace('.', ',')
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
    },
    buy(product) {
        var shoppingCartItem = {
            accountId: this.accountId,
            productId: product.id,
            quantity: 1 };

        cartClient.add(shoppingCartItem)
            .then(data => {
                console.log(JSON.stringify(data));
                this.$store.commit('updateCart', data);
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
