import { Button, Grid, TextField } from '@mui/material'
import React from 'react'
import { useNavigate } from 'react-router-dom';
import {useDispatch} from 'react-redux';
import { login } from '../../../Redux/Auth/Action';

const Login = () => {

    const dispatch=useDispatch();
    const navigate=useNavigate();
    
    const handleLogin=(event)=>{
        event.preventDefault();

        const data = new FormData(event.currentTarget)

        const userData = {
            email: data.get('email'),
            password: data.get('password')
        }

        console.log('userData',userData);
        dispatch(login(userData))
    }
  return (
    <div>
      <form onSubmit={handleLogin}>
        <Grid container spacing={3}>
            <Grid item sm={12}>
                <TextField
                required
                name='email'
                label='Email'
                type='email'
                id='email'
                fullWidth
                autoComplete='email'
                />
            </Grid>
            <Grid item sm={12}>
                <TextField
                required
                name='password'
                label='Password'
                type='password'
                id='password'
                fullWidth
                autoComplete='password'
                />
            </Grid>
            <Grid item xs={12}>
            <Button 
            variant='contained'
            type='submit'
            size='large'
            sx={{padding:".8rem 0"}}
            fullWidth
            >Login</Button>
            </Grid>
            <Grid item sm={6}>
                <h5>Forget Username</h5>
            </Grid>
            <Grid item sm={6}>
                <h5>Forget Password</h5>
            </Grid>
        </Grid>
        <Grid item sm={12} style={{textAlign:"center",marginTop:"30px"}}>
            <h3>Don't have an Account?</h3>
            <Grid item sm={6}>
                <Button onClick={()=>navigate("/register")} variant='contained' sx={{marginTop:"20px"}}>
                    Create Account
                </Button>
            </Grid>
        </Grid>
      </form>
    </div>
  )
}

export default Login
