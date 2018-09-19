<template>
  <div>
    <h3>Hello {{ name }} from my Vue.js page, built with Webpack 4!</h3>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    name: {
      type: String,
      required: true,
    },
  },
  mounted() {
    axios.get('/api/hello').then(res => {
      console.log(res);
    });
  },
};
</script>

<style scoped>
</style>
