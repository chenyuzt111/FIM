import axios from "./axios.js";

async function fetchInventory() {
    try {
        const response = await axios.post('inventory-data.json')
        return response.data;
    }
    catch (err) {
        console.log(err);
    }
}


export default fetchInventory;