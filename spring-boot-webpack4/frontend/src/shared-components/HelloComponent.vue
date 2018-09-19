<template>
  <div class="full-width center-content">
    <h1>Hello {{ name }}</h1>
  </div>
</template>

<script>
/* eslint-disable vue/require-default-prop */

export default {
  name: 'HelloComponent',
  props: {
    name: {
      type: String,
      require: true,
    },
  },
};
</script>

<style scoped>
.full-width {
  width: 100%;
}

.center-content {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
