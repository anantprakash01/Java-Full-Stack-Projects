import React from 'react'
import {
  Link
} from "react-router-dom";
export default function LogoutComponent() {
  return (
    <div className="LogoutComponent container">
            <h2>You are logged out!</h2>
            <h5>Thank you for using our App. Come back soon!
              <Link style={{ color: 'blue' }} className="nav-link" to="/login">Login</Link></h5>
    </div>
  )
}
