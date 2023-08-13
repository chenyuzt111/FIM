import axios from "benewake-order-manegement-system-main/frontend/src/api/axios.js";


const api = axios.create({
    baseURL: 'http://localhost:8080/benewake',
    withCredentials: true
});
api.defaults.withCredentials = true;

export default api;