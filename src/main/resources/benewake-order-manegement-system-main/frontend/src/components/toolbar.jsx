import { useState } from 'react';
import ExcelUploader from './ExcelUploader.jsx';
import * as XLSX from 'xlsx';
import { useAlertContext, useSelectedDataContext, useTableDataContext, useTableStatesContext, useUpdateTabContext, useUpdateTableDataContext, useUpdateTableStatesContext } from '../hooks/useCustomContext.js';;
import { useLocation, useNavigate } from 'react-router-dom';
import { fetchData } from '../api/fetch.js';
import children from '../path/children.jsx';
import moment from 'moment';
moment.updateLocale('zh-cn')
import "moment/dist/locale/zh-cn";
import { deleteInquiry } from '../api/delete.js';
import { startInquiry } from '../api/inquiry.js';
import ColVisibility from './ColVisibility.jsx';
import { getIndexes, EngToCn, VisibilityToHeadersENG } from '../js/transformType.js';
import { noData, isObjectEmpty, noVisibleCols } from '../js/valueCheck.js';
import { getVisbleTableData } from '../js/parseData.js';
import { getTableId } from '../js/getData.js';
import { EDIT_INQUIRY_TAB, NEW_INQUIRY_TAB } from '../constants/Global.jsx';

export default function Toolbar({ features }) {

    const updateTabs = useUpdateTabContext()
    const updateTableData = useUpdateTableDataContext()
    const updateTableStates = useUpdateTableStatesContext()

    const tableData = useTableDataContext()
    const updateAlert = useAlertContext()

    const { selectedQuery, setSelectedData } = useSelectedDataContext()
    const location = useLocation()
    const tableId = getTableId(location)
    const defaultSelection = selectedQuery[tableId]
    const states = useTableStatesContext()

    const { rowSelection } = useTableStatesContext()
    const [openImportPopup, setOpenImportPopup] = useState(false)
    const activeTab = useLocation().pathname.replace("/", "")

    const toggleImportPopup = () => setOpenImportPopup(!openImportPopup)

    function noRowSelected() {
        if (!tableData || tableData.length === 0 || isObjectEmpty(rowSelection)) {
            updateAlert({
                type: "SHOW_ALERT",
                data: { type: "warning", message: "未选择数据！", action: null }
            })
            return true
        }
        else { return false }
    }

    const handleDelete = async () => {
        if (!noRowSelected())
            updateAlert({
                type: "SHOW_ALERT",
                data: {
                    type: "confirm",
                    message: "确定删除选定的信息？",
                    action: async () => {
                        if (!noRowSelected()) {
                            const orderIds = getIndexes(rowSelection)?.map((index) => tableData[index].inquiry_id);
                            await orderIds?.forEach(orderId => deleteInquiry(orderId))
                            updateAlert({
                                type: "SHOW_ALERT",
                                data: { type: "success", message: "删除成功！", action: null }
                            })
                            updateTableData({ type: "DELETE_ROWS", rowSelection: rowSelection })
                            updateTableStates({ type: "RESET_ROW_SELECTION" })
                        }
                    }
                }
            })
    }

    const handleRefresh = async () => {
        updateTableData({ type: "CLEAR_TABLE_DATA" })

        const res = await fetchData(selectedQuery[tableId])
        updateTableData({ type: "SET_TABLE_DATA", tableData: res.lists })
    }

    const handleExport = () => {
        if (noData(tableData) || noVisibleCols(states.columnVisibility)) {
            updateAlert({ type: "SHOW_ALERT", data: { type: "error", message: "没有数据！" } })
        }
        else {
            updateAlert({
                type: "SHOW_ALERT", data: {
                    type: "confirm", message: "确定导出该表单？", action: () => {

                        const wb = XLSX.utils.book_new();
                        const ws = XLSX.utils.json_to_sheet([]);

                        const headers_ENG = VisibilityToHeadersENG(states.columnVisibility)
                        let headers_CN = headers_ENG.map((name) => EngToCn(name)).filter((value) => value !== undefined)

                        XLSX.utils.sheet_add_aoa(ws, [headers_CN]);
                        const newData = getVisbleTableData(tableData, headers_ENG)

                        XLSX.utils.sheet_add_json(ws, newData, { origin: 'A2', skipHeader: true });
                        XLSX.utils.book_append_sheet(wb, ws);

                        const timestamp = moment(new Date()).format('YYMMDDHHmmss')
                        const filename = children.filter((child) => child.path === activeTab)[0].name

                        XLSX.writeFileXLSX(wb, filename + timestamp + ".xlsx");
                    }
                }
            })

        }
    }

    const handleStartInquiry = async () => {
        if (!noRowSelected()) {
            const indexes = getIndexes(rowSelection)
            const inquiries = indexes.map(i => tableData[i])
            let newInquiries = inquiries.map(obj => ({ "inquiryId": obj.inquiry_id }));

            const res = await startInquiry(newInquiries, 1)
            switch (res.code) {
                case 200:
                    updateAlert({ type: "SHOW_ALERT", data: { type: "warning", message: res.message } })
                    handleRefresh()
                    break
                case 400:
                    updateAlert({ type: "SHOW_ALERT", data: { type: "error", message: res.message } })
                    break
                case 1:
                    updateAlert({ type: "SHOW_ALERT", data: { type: "error", message: res.message } })
                    break
                default:
                    throw new Error("Unknown inquiry problem")
            }
        }
    }

    const handleEdit = async () => {
        if (!noRowSelected()) {
            if (Object.keys(rowSelection).length > 1) {
                updateAlert({ type: "SHOW_ALERT", data: { type: "error", message: "至多一条询单进行修改！" } })
            }
            else {
                const newTab = EDIT_INQUIRY_TAB
                updateTabs({ type: "ADD_TAB", tab: newTab })
                const selectedIndex = Number(Object.keys(rowSelection)[0])
                const selecteData = tableData[selectedIndex]
                setSelectedData(selecteData)
                navigate("/edit")
            }
        }
    }

    const handlePin = () => {
        const pinnedIndexes = getIndexes(rowSelection)
        const rowData = tabContents[activeTab].filter((_, i) => pinnedIndexes.includes(i));
        setPinnedRows(rowData)
        deleteSelectedRows()
    }

    const handleUnpin = () => {
        //TODO
    }

    const navigate = useNavigate()

    const handleNew = () => {
        const newTab = NEW_INQUIRY_TAB
        updateTabs({ type: "ADD_TAB", tab: newTab })
        navigate("/new")
    }

    const importPopup =
        <div className="popup-container flex-center">
            <ExcelUploader close={toggleImportPopup} updateAlert={updateAlert} />
        </div>


    return (
        <div className='row toolbar' >
            <div className='row flex-center'>
                <button to="/new" onClick={handleNew} className={`${features?.includes("new") ? "" : "hidden"}`}>新增</button>

                <button onClick={handleDelete} className={`${features?.includes("delete") ? "" : "hidden"}`}>删除</button>

                <button onClick={handlePin} className={`${features?.includes("pin") ? "" : "hidden"}`}>置顶</button>

                <button onClick={handleUnpin} className={`${features?.includes("unpin") ? "" : "hidden"}`}>取消置顶</button>

                <button onClick={handleRefresh} className={`${features?.includes("refresh") ? "" : "hidden"}`}>刷新</button>

                <button onClick={toggleImportPopup} className={`${features?.includes("import") ? "" : "hidden"}`}>导入</button>
                {openImportPopup && importPopup}

                <button onClick={handleExport} className={`${features?.includes("export") ? "" : "hidden"}`}>导出</button>

                <button onClick={handleEdit} className={`${features?.includes("edit") ? "" : "hidden"}`}>修改</button>

                <button onClick={handleStartInquiry} className={`${features?.includes("startInquiry") ? "" : "hidden"}`}>开始询单</button>
            </div>
            {
                features.includes("visibility")
                && !noData(tableData)
                &&
                <div className="row flex-center status">
                    <ColVisibility editable={tableId === 1 && defaultSelection.viewId > 0} />
                </div>
            }
        </div >
    )
}
