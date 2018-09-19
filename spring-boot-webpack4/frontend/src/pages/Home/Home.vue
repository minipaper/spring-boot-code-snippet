<template>
  <div class="full-width center-content">
    <img src="img/logo.png">
    <welcome-message name="World"/>
  </div>
</template>

<script>
import WelcomeMessage from './component/WelcomeMessage.vue';

export default {
  components: {
    WelcomeMessage,
  },
};
</script>


<style>
.full-width {
  width: 100%;
}

.center-content {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
