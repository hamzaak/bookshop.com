<template>
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
        <tr v-for="p of products" :key="p.id.timestamp">
          <td>{{p.title}}</td>
          <td>{{p.description}}</td>
          <td align="right">
              <button class="btn btn-danger" type="button">
                Buy ${{formatPrice(p.price)}}
              </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  name: "Products",
  props: {
    msg: String,
  },
  data() {
    return {
      products: [],
      errors: []
    }
  },
  created() {
    axios.get(`http://192.168.64.3:9001/api/products/get/all`)
    .then(response => {
      // JSON responses are automatically parsed.
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
