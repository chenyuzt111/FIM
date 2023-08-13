import { useMemo, useState } from 'react';
import SecTabs from '../components/SecTabs.jsx'
import Table from '../components/Table.jsx';
import Views from '../components/Views.jsx';
import Toolbar from '../components/toolbar.jsx';
import { useTableDataContext } from '../hooks/useCustomContext.js';
import { inquiryViews } from '../constants/Views.js'
import AllDefs from '../constants/AllDefs.jsx';

// 全部订单
export default function Inquiry() {
  const tableData = useTableDataContext()
  const columns = useMemo(() => AllDefs, [])
  const features = ["delete", "export", "refresh", 'visibility']
  const [views, setViews] = useState(inquiryViews)

  return (
    <div className='col full-screen'>
      <div className="tab-contents">
        <Toolbar features={features} />
        <SecTabs />
        <Views
          views={views}
          setViews={setViews}
        />
      </div>
      {tableData &&
        <div className='content-container col'>
          <Table
            data={tableData}
            columns={columns}
          />
        </div>
      }
    </div>
  )
}
