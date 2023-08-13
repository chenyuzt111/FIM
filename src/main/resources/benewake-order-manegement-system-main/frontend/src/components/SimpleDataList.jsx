import { useState, useEffect } from 'react';
import api from '../api/axios.js';
import localOptions from '../constants/LocalOptions.json'
import { fetchStateNums } from '../api/fetch.js';
import { getStateStr } from '../js/parseData.js';

function getOptionName(listName, option) {
    switch (listName) {
        case "customer_name":
            return option.fname
        case "salesman_name":
            return option.username
        case "created_user_name":
            return option.username
        case "item_code":
            return option.itemCode
        case "inquiry_code":
            return option.inquiryCode
        case "item_name":
            return option.itemName
        case "delivery_code":
            return option.deliveryCode
        default:
            return option
    }
}

function getFuzzyMatchResult(value, allOptions) {
    return allOptions.filter(
        option => option.toLowerCase().includes(value.toLowerCase())
    )
}
const SimpleDataList = ({ name, initialValue, initialOptions, handleChange, searchKey, url }) => {
    const [options, setOptions] = useState(null)
    const [showDropdown, setShowDropdown] = useState(false);
    const [value, setValue] = useState("")
    const [stateOptions, setStateOptions] = useState()

    useEffect(() => {
        setValue(initialValue);
    }, [initialValue])

    useEffect(() => {
        if (name === "state") {
            async function fetch() {
                const res = await fetchStateNums()
                setStateOptions(res.data.map(num => getStateStr(num)))
            }
            fetch()
        }
    }, [])

    const onChange = async (e) => {
        setValue(e.target.value)
        if (initialOptions) {
            setOptions(initialOptions)
            handleChange()
        }
        else {
            if (!url) {
                const allOptions = name === "state" ? stateOptions : localOptions[name]
                const fuzzyMatchResults = getFuzzyMatchResult(e.target.value, allOptions)

                setOptions(fuzzyMatchResults)
            }
            else {
                const res = (await api.post(url, { [searchKey]: e.target.value }))
                setOptions(res?.data?.data)
            }
            handleChange("value", e.target.value)
        }

        setShowDropdown(true);
    }

    const handleSelect = (option) => {
        setValue(getOptionName(name, option, searchKey))
        if (initialOptions) {
            handleChange()
        }
        else {
            handleChange("value", getOptionName(name, option, searchKey))
        }
        setShowDropdown(false)
    };

    const clearData = () => {
        setShowDropdown(false)
        setOptions(null)
    }

    return (
        <div className="data-list"
            onMouseLeave={clearData}
        >
            <input
                type="text"
                value={value}
                onChange={onChange}
                name={name}
                onFocus={() => setShowDropdown(true)}
            />
            {
                showDropdown && options &&
                <ul
                    className="data-list-dropdown"
                    onMouseLeave={clearData} >
                    {
                        name === "item_code" &&  options.length > 0 &&
                        < li className='row sticky' >
                            <div>物料编码</div><div>物料名称</div>
                        </li>
                    }
                    {
                        options.length > 0
                            ? options.map((option, i) =>
                                name !== "item_code"
                                    ? <li key={i}
                                        onClick={() => handleSelect(option)}>
                                        {getOptionName(name, option, searchKey)}
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
}

export default SimpleDataList;
