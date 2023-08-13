import DatePicker from '../components/DatePicker.jsx';
import EditTable from '../components/EditTable.jsx';
import { useState, useEffect } from 'react';

import { startInquiry, updateInquiry } from '../api/inquiry.js';
import { parseInquiryObj, rowToInquiry } from '../js/parseData.js';
import moment from 'moment';
import { useAlertContext, useSelectedDataContext } from '../hooks/useCustomContext.js';
import { snakeToCamelCase } from '../js/transformType.js';

const SimpleToolbar = ({ rows, ids, setIds }) => {
    const updateAlert = useAlertContext()
    const [action, setAction] = useState(null)

    const handleSaveClick = async () => {
        setAction({ type: "保存", time: new Date() })

        const newInquiries = await Promise.all(rows.map(row => rowToInquiry(row)));

        const res = await updateInquiry(newInquiries[0])
        switch (res.code) {
            case 200:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "success",
                        message: res.message
                    }
                })
                setIds([res.data])
                break
            case 400:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "error",
                        message: res.message
                    }
                })
                break
            case 1:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "error",
                        message: res.message
                    }
                })
                break
            default:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "error",
                        message: "未知错误"
                    }
                })
                break
        }
    }

    const handleStartClick = async () => {
        setAction({ type: "开始询单", time: new Date() })

        let newInquiries;
        if (ids) { newInquiries = ids.map((id) => ({ "inquiryId": id })) }

        const res = await startInquiry(newInquiries, 1)
        switch (res.code) {
            case 200:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "warning",
                        message: res.message
                    }
                })
                break
            case 400:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "error",
                        message: res.message
                    }
                })
                break
            case 1:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "error",
                        message: res.message
                    }
                })
                break
            default:
                updateAlert({
                    type: "SHOW_ALERT", data: {
                        type: "error",
                        message: "未知错误"
                    }
                })
                break
        }

    }


    return (
        <div className='row toolbar' >
            <div className='row flex-center'>
                <button onClick={handleSaveClick} >保存</button>
                <button onClick={handleStartClick}>开始询单</button>
            </div>
            <div className="row flex-center status">
                {action &&
                    <span>
                        <strong>{action.type}</strong>
                        &nbsp;于&nbsp;
                        <strong>{`${moment(action.time).format('lll')}`}</strong>
                    </span>
                }
            </div>
        </div >
    )

}

const Edit = () => {
    const { selectedData } = useSelectedDataContext()
    let initialData;
    const [ids, setIds] = useState(null)
    const [rows, setRows] = useState(null)
    useEffect(() => {
        async function fetch() {
            initialData = await (parseInquiryObj(selectedData))
            Object.entries(selectedData).forEach(([key, value]) => {
                const camelCaseKey = snakeToCamelCase(key)
                initialData[camelCaseKey] = value
            })
            setRows([initialData])
            setIds([selectedData.inquiry_id])
        }
        fetch()
    }, [])

    const [currentDate, setCurrentDate] = useState(new Date());

    return (
        <div className='col full-screen invoice-container'>
            <SimpleToolbar rows={rows} ids={ids} setIds={setIds} />
            <div className='col inquiry-info'>
                <div className='row input-wrapper'>
                    <h1>单据编号：</h1>
                    <input type="text" readOnly name="inquiryCode" value={(selectedData?.inquiry_code || selectedData?.inquiryCode) ?? ""} className='inquiry-code' />
                </div>
                <div className="react-datepicker-container input-wrapper">
                    单据日期：
                    <DatePicker
                        selected={currentDate}
                        onChange={(date) => setCurrentDate(date)
                        }
                    />
                </div>
            </div>
            {rows && <EditTable rows={rows} setRows={setRows} />}
        </div >

    )
}

export default Edit