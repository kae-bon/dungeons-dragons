<template>
  <div id="capstone-app" class="d-flex flex-column">
    <SiteHeader/>
    <router-view id="router-view" />
    <SiteFooter/>
  </div>
</template>

<script>
import SiteHeader from './components/SiteHeader.vue'
import SiteFooter from './components/SiteFooter.vue'
export default {
  components: {SiteHeader, SiteFooter}
}

</script>

<style scoped>
#capstone-app {
  height: 100vh;
}

#router-view {
  flex-grow: 1;
}
</style>