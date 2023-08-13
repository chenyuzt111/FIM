import User from '../routes/User.jsx';
import All from '../routes/All.jsx';
import Charts from '../routes/charts.jsx';
import New from '../routes/New.jsx';
import NotFound from '../routes/NotFound.jsx';
import fetchInventory from '../api/test.js';
import Production from '../routes/Production.jsx';
import Order from '../routes/Order.jsx';
import Customer from '../routes/Customer.jsx';
import Item from '../routes/Item.jsx';
import Inquiry from '../routes/Inquiry.jsx';
import Delivery from '../routes/Delivery.jsx';
import Inventory from '../routes/Inventory.jsx';
import Sales from '../routes/Sales.jsx';
import Edit from '../routes/Edit.jsx';
import { fetchNewViews } from '../api/fetch.js';

const children = [
    {
        name: "全部订单", path: "all", id: 1,
        element: <All />,
        loader: async () => {
            const viewRes = await fetchNewViews("1")
            return {
                newViews: viewRes.data,
            }
        }

    },
    { name: "订单类型列表", path: "order", element: <Order />, id: 2 },
    { name: "客户类型列表", path: "customer", element: <Customer />, id: 3 },
    { name: "产品类型列表", path: "item", element: <Item />, id: 4 },
    { name: "订单转换列表", path: "inquiry", element: <Inquiry />, id: 5 },
    { name: "订单交付进度", path: "delivery", element: <Delivery />, id: 6 },
    // { name: "库存占用情况", path: "inventory", element: <Inventory /> },
    // { name: "生产计划", path: "production", element: <Production /> },
    // { name: "仪表盘", path: "charts", element: <Charts /> },
    // { name: "销售计划", path: "sales", element: <Sales /> },
    { name: "用户主页", path: "user", element: <User /> },
    { name: "新增询单", path: "new", element: <New /> },
    { name: "修改询单", path: "edit", element: <Edit /> },
    { name: "404", path: "*", element: <NotFound /> }
]

export default children;
