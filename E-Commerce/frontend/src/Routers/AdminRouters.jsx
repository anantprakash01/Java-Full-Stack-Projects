import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Admin from '../Admin/components/Admin'

const AdminRouters = () => {
  return (
    <div>
      <Routes>
        <Route path='/*' element={<Admin/>}></Route>
      </Routes>
    </div>
  )
}

export default AdminRouters
