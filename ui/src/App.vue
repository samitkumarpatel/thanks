<template>
  <div id="app" class="container">
    <Header />
    <router-view/>
    <Footer />
  </div>
</template>

<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'

export default {
  name: "app",
  components: {
    Header,Footer
  },
  data() {
    return {
      title : 'app'
    }
  },

};
</script>

<style>
  .app_body{
    margin-top: 30px;
  }
</style>
