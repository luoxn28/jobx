<template>
  <div id="error404">
    <p>
      ███████╗██████╗ ██████╗  ██████╗ ██████╗     ██╗  ██╗ ██████╗ ██╗  ██╗<br/>
      ██╔════╝██╔══██╗██╔══██╗██╔═══██╗██╔══██╗    ██║  ██║██╔═████╗██║  ██║<br/>
      █████╗  ██████╔╝██████╔╝██║   ██║██████╔╝    ███████║██║██╔██║███████║<br/>
      ██╔══╝  ██╔══██╗██╔══██╗██║   ██║██╔══██╗    ╚════██║████╔╝██║╚════██║<br/>
      ███████╗██║  ██║██║  ██║╚██████╔╝██║  ██║         ██║╚██████╔╝     ██║<br/>
      ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝         ╚═╝ ╚═════╝      ╚═╝<br/>
    </p>
    <h1>{{ msg }}</h1>
    <h4>{{ notice }}</h4>
    <h4><a href="/">jobx</a></h4>
  </div>
</template>

<script>
  export default
  {
    name: 'Error404',
    data () {
      return {
        msg:  '404页面',
        notice: 'jobx -- A distributed task scheduling system.'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #error404 {
    text-align: center;
    color: brown;
  }
</style>
