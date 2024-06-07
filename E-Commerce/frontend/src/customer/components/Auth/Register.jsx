import { Button, Grid, TextField } from '@mui/material';
import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { getUser, register } from '../../../Redux/Auth/Action';
import { store } from '../../../State/store';

const Register = () => {

    const dispatch = useDispatch()
    const jwt = localStorage.getItem("jwt")
    const {auth} = useSelector((store)=>store)

    useEffect(()=>{
        if(jwt){
            dispatch(getUser(jwt))
        }
    },[jwt,auth.jwt])

    const handleSubmit=(event)=>{
        event.preventDefault()
        const data = new FormData(event.currentTarget);

        const userData={
            firstName:data.get("firstName"),
            lastName:data.get("lastName"),
            email:data.get("email"),
            password:data.get("password")
        }
        dispatch(register(userData))
        console.log("userData ",userData);
    }
  return (
    <div>
      <h2 className='mb-5'>Create Account</h2>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
            <Grid item sm={6}>
                <TextField
                required
                id='firstName'
                name='firstName'
                label='First Name'
                fullWidth
                autoComplete='given-name'
                />
            </Grid>
            <Grid item sm={6}>
                <TextField
                required
                id='lastName'
                name='lastName'
                label='Last Name'
                fullWidth
                autoComplete='given-name'
                />
            </Grid>
            <Grid item sm={12}>
                <TextField
                required
                id='email'
                name='email'
                label='Email'
                fullWidth
                autoComplete='email'
                />
            </Grid>
            <Grid item sm={12}>
                <TextField
                required
                id='password'
                name='password'
                label='Password'
                type='password'
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
            >Create Account</Button>
            </Grid>
            
            <Grid item spacing={3}>
                <h5>Already Have an Account? <Link to={"/Login"} style={{color:"blue" ,marginLeft:"10px"}}> Sign in</Link></h5>
            </Grid>
        </Grid>
      </form>
    </div>
  )
}

export default Register
