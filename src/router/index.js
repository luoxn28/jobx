import Vue from 'vue'
import Router from 'vue-router'
import router from '../router'

Vue.use(Router)

import Error404 from '../components/Error404.vue'
import HelloWorld from '../components/HelloWorld.vue'

var routes = [
  {path: "/", name: 'HelloWorld', meta: {title: 'jobx测试平台', filter: false}, component:HelloWorld},
  {path: '*', name: 'Error404', meta: {title: '404', filter: false}, component: Error404}
];

export default new Router({
  mode: 'history',
  routes: routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }

  next();
});
