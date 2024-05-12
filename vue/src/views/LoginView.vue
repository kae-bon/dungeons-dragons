<template>
  <div class="container form form-floating">
    <div class="mb-5">
      <h1>Your Adventure Awaits!</h1>
      <h2 class="fst-italic">please log in</h2>

      <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
      </div>
    </div>
   
    <form class="d-flex flex-column align-items-center" v-on:submit.prevent="login">
      
      <div class="mb-3 input">
        <label for="username" class="form-label">username</label>
        <input type="text" class="form-control" id="username" placeholder="adventurous-aardvark" v-model="user.username" required autofocus>
      </div>
      <div class="mb-3 input">
        <label for="password" class="form-label">password</label>
        <input type="password" class="form-control" id="password" v-model="user.password" required>
      </div>
      <button class="btn btn-primary mb-4" type="submit">log in</button>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
    
    </form>
  </div>
  
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
h2 {
  font-size: 1.2rem;
}

.input {
  width: 100%;
}

a {
  color: #219ebc;
  text-decoration: none;
  transition: .15s color;
}

a:hover {
  color: #f4a261;
  transition: .15s color;
}

@media screen and ( min-width: 400px ) {
    .input {
      width: 75%;
    }

}

@media screen and ( min-width: 750px ) {
    .input {
      width: 50%;
    }
}
</style>