<template>
  <div id="capstone-app">
    <SiteHeader/>
    
    <router-view />
  </div>
</template>

<script>
import SiteHeader from './components/SiteHeader.vue'
export default {
  components: {SiteHeader}
}

</script>