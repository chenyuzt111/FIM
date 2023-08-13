
import TabProvider from './providers/TabProvider.jsx';
import RequireAuth from './components/RequireAuth.jsx';
import TableProvider from './providers/TableProvider.jsx';
import SelectedDataProvider from './providers/SelectedDataProvider.jsx';
import Layout from './components/Layout.jsx';

export default function App() {
    return (
        <TabProvider>
            <SelectedDataProvider>
                <TableProvider>
                    <Layout>
                        <RequireAuth />
                    </Layout>
                </TableProvider>
            </SelectedDataProvider>
        </TabProvider>
    )
}
