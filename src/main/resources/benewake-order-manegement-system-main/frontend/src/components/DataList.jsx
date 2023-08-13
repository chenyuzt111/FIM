import { memo, useState, useEffect } from 'react';
import { fetchOptions } from '../api/fetch.js';

const inquiryTypeOptions = [{ inquiryType: "PO(客户付款)" }, { inquiryType: "PR(客户提出付款意向)" }, { inquiryType: "YG(供应链预估)" }, { inquiryType: "YC(销售预测)" }, { inquiryType: "XD(意向询单)" }]

const getOptionName = (type, option) => {
    switch (type) {
        case "customer":
            return option.fname
        default:
            return option[searchKey]
    }
}
const DataList = memo(function DataList({ type, searchKey, initialValue, handleChange, identifier }) {
    const [options, setOptions] = useState(null)
    const [showDropdown, setShowDropdown] = useState(false);
    const [value, setValue] = useState("")

    useEffect(() => { setValue(initialValue) }, [initialValue])

    const onChange = async (e) => {
        setValue(e.target.value)
        if (identifier !== "inquiryType") {
            const res = (await fetchOptions(type, searchKey, e.target.value))
            setOptions(res)
        }
        else {
            setOptions(inquiryTypeOptions)
        }
        setShowDropdown(true);

    }

    const handleSelect = (option) => {
        if (identifier === "itemCode") {
            setValue(option.itemCode)
            handleChange(["itemCode", "itemName", "itemType", "itemId"], [option.itemCode, option.itemName, option.itemType, option.id])
        }

        else if (identifier === "customerName") {
            setValue(option.fname)
            handleChange(["customerName", "customerId"], [option.fname, option.fcustId])
        }

        else if (identifier === "salesmanName") {
            setValue(option.username)
            handleChange(["salesmanName", "salesmanId"], [option.username, option.id])
        }
        else {
            setValue(option[searchKey])
            handleChange([identifier], [option[searchKey]]);
        }
        setShowDropdown(false)
    };

    const clearData = () => {
        if (value?.length > 0) {
            if (identifier === "itemCode") {
                handleChange(["itemCode", "itemName", "itemType", "itemId", "customerType"], ["", "", "", "", ""])
            }

            else if (identifier === "customerName") {
                handleChange(["customerName", "customerId", "customerType"], ["", "", ""])
            }
            else {
                handleChange([identifier], [""]);
            }
            setValue("")
        }
        setShowDropdown(false)
    }

    return (
        <div className="data-list" >
            <input
                type="text"
                value={value}
                onChange={onChange}
                onFocus={() => setShowDropdown(true)}
            />
            {
                showDropdown && options &&
                <ul
                    className="data-list-dropdown"
                    onMouseLeave={clearData} >
                    {
                        identifier === "itemCode" &&
                        < li className='row sticky' >
                            <div>物料编码</div><div>物料名称</div>
                        </li>
                    }
                    {
                        options.length > 0
                            ? options.map((option, i) =>
                                identifier !== "itemCode"
                                    ? <li key={i}
                                        onClick={() => handleSelect(option)}>
                                        {getOptionName(type, option)}
                                    </li>
                                    : <li className='row' key={i}
                                        onClick={() => handleSelect(option)}>
                                        <div>{option.itemCode}</div>
                                        <div>{option.itemName}</div>
                                    </li>
                            )
                            : <li onClick={clearData} >无匹配结果</li>
                    }
                </ul>
            }
        </div >
    );
})

export default DataList;
