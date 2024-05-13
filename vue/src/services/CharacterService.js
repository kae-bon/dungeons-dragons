import axios from 'axios';

export default {

  getAllCharacters() {
    return axios.get('/users/characters');
  },

  editCharacter(id, character) {
    return axios.put(`/characters/${id}`, character)
  },

  getCharacterById(id) {
    return axios.get(`/characters/${id}`);
  },

  addClassSubclassToCharacter(id, classSubclass) {
    return axios.post(`/characters/${id}/classes`, classSubclass);
  }

}