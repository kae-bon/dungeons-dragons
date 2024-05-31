import axios from "axios";

export default {
  getAllClassesAndSubclasses() {
    return axios.get("/classes");
  },

  getSubclassesByClassName(className) {
    return axios.get(`/subclasses?className=${className}`);
  },
};
