import { Button, Card, CardContent, Typography, styled } from '@mui/material'
import React from 'react'

const TriangleImg = styled('img')({
    right: 0,
    bottom: 0,
    height: 170,
    position: 'absolute'
  })

  const TrophyImg = styled('img')({
    right: 36,
    bottom: 20,
    height: 98,
    position: 'absolute'
  })
const Achievement = () => {
  return (
    <Card sx={{ position: 'relative' }}>
      <CardContent>
      <Typography variant='h6' sx={{ letterSpacing: '0.25px' }}>
          Shop With Zosh
        </Typography>
        <Typography variant='body2' >Congratulations 🥳</Typography>
        
        <Typography variant='h5' sx={{ my: 3.1, color: 'primary.main' }}>
          420.8k
        </Typography>
        <Button size='small' variant='contained'>
          View Sales
        </Button>
        {/* <TriangleImg alt='triangle background' src='' /> */}
        <TrophyImg alt='trophy' src='https://tse3.mm.bing.net/th?id=OIP.30z2Lgbnbj0P8H-BGn4bbAHaGc&pid=Api&P=0&h=180' />
      </CardContent>
    </Card>
  )
}

export default Achievement
