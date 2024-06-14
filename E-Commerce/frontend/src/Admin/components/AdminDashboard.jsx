import { Grid } from '@mui/material'
import React from 'react'
import Achievement from './Achievement'
import MonthlyOverview from './MonthlyOverview'

const AdminDashboard = () => {
  return (
    <div className='p-10'>
      <Grid container spacing={2}>
        <Grid item xs={12} md={4}>
          <Achievement></Achievement>
        </Grid>
        <Grid item xs={12} md={8}>
          <MonthlyOverview></MonthlyOverview>
        </Grid>
        <Grid item xs={12} md={6}>
          
        </Grid>
      </Grid>
    </div>
  )
}

export default AdminDashboard
