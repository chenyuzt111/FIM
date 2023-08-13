import { useState } from 'react';
import { useSelectedDataContext, useUpdateTableDataContext } from '../hooks/useCustomContext.js';
import { fetchData } from '../api/fetch.js';
import { useLocation } from 'react-router-dom';
import { getTableId } from '../js/getData.js';

const SecTabs = () => {


    const updateTableData = useUpdateTableDataContext()
    const location = useLocation()
    const tableId = getTableId(location)
    const { selectedQuery, updateSelectedQuery } = useSelectedDataContext()
    const defaultSelection = selectedQuery[tableId]
    const [activeSecTab, setActiveSecTab] = useState(defaultSelection.secTab);

    const handleSecTabClick = async (secTab) => {
        setActiveSecTab(secTab);
        updateSelectedQuery(tableId, "secTab", secTab)
        updateTableData({ type: "CLEAR_TABLE_DATA" })

        const res = await fetchData({ ...defaultSelection, secTab })
        updateTableData({ type: "SET_TABLE_DATA", tableData: res.lists })
    }
    const secTabs = ['已完成', '未过期未完成', '已过期未完成']

    return (
        <div className="sec-tab-wrapper row">
            {secTabs.map((secTab, i) =>
                <div key={i}
                    className={`secondary-tab 
                    ${activeSecTab === secTab ? "active" : ""}`}
                    onClick={() => handleSecTabClick(secTab)}>
                    {secTab}
                </div>
            )}
        </div>
    )
}


export default SecTabs;
