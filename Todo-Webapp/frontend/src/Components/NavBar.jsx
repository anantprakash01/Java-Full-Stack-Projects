import React from 'react'
import {Link} from "react-router-dom";
import { useAuth } from './security/AuthContext';


export default function NavBar() {


  
  const authContext = useAuth()
  const isAuthenticated = authContext.isAuthenticated

  function logout() {
    authContext.logout()
  }

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary  border-bottom border-light border-5  p-2">
  <div className="container-fluid">
    <h5 className="navbar-brand">Taskify</h5>
    {/* <a className="navbar-brand" href="#">Todo Manager</a> */}
    <button  className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <div className="collapse navbar-collapse" id="navbarSupportedContent">
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
        { isAuthenticated &&
          <Link className="nav-link" to="/welcome/Anant">Home</Link>
        }
        </li>
        <li className="nav-item">
          { isAuthenticated &&
            <Link className="nav-link" to="/todos">Tools</Link>
          }
        </li>
      </ul>

      <ul className="navbar-nav">
        <li className="nav-item">
        {!isAuthenticated &&
          <Link className="nav-link" to="/login">Login</Link>
        }
        </li>
        <li className="nav-item">
        { isAuthenticated &&
          <Link className="nav-link" to="/logout" onClick={logout}>Logout</Link>
        }
        </li>
      </ul>
      
    </div>
  </div>
</nav>
  )
}
