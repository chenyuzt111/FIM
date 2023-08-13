import { memo, useMemo, useState, useEffect } from 'react';
import SecTabs from '../components/SecTabs.jsx'
import Table from '../components/Table.jsx';
import Views from '../components/Views.jsx';
import Toolbar from '../components/toolbar.jsx';
import { useTableDataContext } from '../hooks/useCustomContext.js';
import { allViews } from '../constants/Views.js';
import AllDefs from '../constants/AllDefs.jsx';

const All = () => {
  const tableData = useTableDataContext()
  const columns = useMemo(() => AllDefs, [])
  const features = ["new", "delete", "import", "export", "edit", "startInquiry", "refresh", 'visibility']
  const [views, setViews] = useState(allViews)


  return (
    <div className='col full-screen'>
      <div className="tab-contents">
        <Toolbar features={features} />
        <SecTabs />
        <Views
          views={views}
          setViews={setViews}
          editable
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

export default memo(All);