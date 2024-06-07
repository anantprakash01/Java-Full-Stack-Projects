import React from 'react'
import { Link } from 'react-router-dom'

const CreateAcc = () => {
    const handleSubmit=(e)=>{
        e.preventDefault();
        console.log(e.target.value);
        console.log("Clicked")
    }
    const handleOnChange=(e)=>{
        console.log(e.target.value)
    }
  return (
    <div className='container items-center text-center '>
     <div className='m-5' style={{minHeight:'650px'}}>
        <h2 className='m-3'>Create Your Taskify Account</h2>
        <h6>Already have an Account ?<Link to={'/login'}> Sign In</Link></h6>
        <form className='form m-3' action="">
            <div>
                <label htmlFor="Email">E-mail:</label>
                <input 
                
                onChange={handleOnChange}
                type="email" />
            </div>
            <div>
                <label htmlFor="name">Full Name:</label>
                <input 
                onChange={handleOnChange}
                type="text" />
            </div>
            <div>
                <label htmlFor="username">Username:</label>
                <input 
                onChange={handleOnChange}
                type="text" />
            </div>
            <div>
                <label htmlFor="password">Password:</label>
                <input 
                onChange={handleOnChange}
                type="password" />
            </div>
            <button  onClick={()=>handleSubmit()} className='btn btn-success m-5'>Submit</button>
        </form>
        </div>
    </div>
  )
}

export default CreateAcc
