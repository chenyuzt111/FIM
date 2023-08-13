import { useEffect } from 'react'
import Toolbar from '../components/toolbar.jsx';
import Table from '../components/Table.jsx'
import InventoryDefs from '../constants/InventoryDefs.jsx';
import { useLoaderData } from 'react-router-dom';
import {useTabContext} from '../hooks/useCustomContext.js';;

// 库存占用情况
export default function Inventory() {

  return (
    <div className='col full-screen'>
      <Toolbar features={['pin', 'unpin', 'refresh', 'export']} />
    </div>
  )
}
