import { createStore as _createStore } from "vuex";
import axios from "axios";

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || "",
      user: currentUser || {},
      characters: [],
      showAlert: false,
      classes: [],
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem("token", token);
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem("user", JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        state.token = "";
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_CHARACTERS(state, characterList) {
        state.characters = characterList;
      },
      DISMISS_ALERT(state) {
        state.showAlert = false;
      },
      SHOW_ALERT(state) {
        state.showAlert = true;
      },
      SET_CLASSES(state, classes) {
        state.classes = classes;
      },
    },
  });
  return store;
}
