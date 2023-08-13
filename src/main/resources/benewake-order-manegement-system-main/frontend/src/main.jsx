import React from 'react'
import ReactDOM from 'react-dom/client'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import './index.css'
import "the-new-css-reset/css/reset.css";
import App from './App.jsx';
import children from './path/children.jsx';
import Login from './routes/login.jsx';
import AuthProvider from './providers/AuthProvider.jsx'
import AlertProvider from './providers/AlertProvider.jsx';
import adminChildren from './path/adminChildren.jsx';


const router = createBrowserRouter([
  {
    path: "/login",
    element: <Login />
  },
  {
    path: "/",
    element: <App />,
    children: children
  },
  {
    path: "/admin",
    element: <App />,
    children: adminChildren
  }

]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AuthProvider>
      <AlertProvider>
        <RouterProvider router={router} />
      </AlertProvider>
    </AuthProvider>
  </React.StrictMode>
)
