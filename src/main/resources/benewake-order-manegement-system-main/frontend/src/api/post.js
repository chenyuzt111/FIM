import api from "./axios.js";

//导入excel
export async function postExcelFile(file) {
    try {
        const response = await api.post('/order/importExcel', {
            file
        }, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        })
        return response.data;
    }
    catch (err) {
        console.log(err);
    }
}

//新建视图
export async function postView({ tableId, viewName, cols, viewId}) {
    try {
        const response = await api.post('/order/saveView', {
            tableId, viewName, cols, viewId
        })
        return response.data;
    }
    catch (err) {
        console.log(err);
    }
}
