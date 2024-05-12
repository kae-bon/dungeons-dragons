<template>
  <div id="register" class="container form form-floating">
    <div class="mb-5">
      <h1>Your Adventure Awaits!</h1>
      <h2 class="fst-italic">create an account</h2>
    </div>
    <form v-on:submit.prevent="register" class="d-flex flex-column align-items-center">
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group mb-3 input">
        <label for="username" class="form-label">Username</label>
        <input type="text" id="username" class="form-control" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group mb-3 input">
        <label for="password" class="form-label">Password</label>
        <input type="password" id="password" class="form-control" v-model="user.password" required />
      </div>
      <div class="form-input-group mb-3 input">
        <label for="confirmPassword" class="form-label">Confirm Password</label>
        <input type="password" id="confirmPassword" class="form-control" v-model="user.confirmPassword" required />
      </div>
      <button class="btn btn-primary mb-4" type="submit">Create Account</button>
      <router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'This username is already taken!',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
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
