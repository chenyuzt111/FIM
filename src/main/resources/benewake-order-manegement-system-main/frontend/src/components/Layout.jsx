import Navbar from "./Navbar.jsx";
import Sidebar from "./sidebar.jsx";
import { useState } from "react";

const Layout = ({ children }) => {
    const [showSidebar, setShowSidebar] = useState(true);

    return (
        <div id="app" className="container">
            <Navbar
                showSidebar={showSidebar}
                setShowSidebar={setShowSidebar}
            />
            <div className='row'>
                <Sidebar showSidebar={showSidebar} />
                {children}
            </div>
        </div>)
}

export default Layout