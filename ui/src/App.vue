<template>
  <div id="app">
    <Navbar />
    <Products />
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'
import Products from './components/Products.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    Products
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
