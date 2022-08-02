<template>
  <img class="first" :src="logo">
</template>

<script>

export default {
  data() {
    return {
      logo: require('../assets/images/login.jpg')
    };
  },
};
</script>

<style>
  .first{
    width: 1550px;
    height: 750px;
  }
</style>


