import React, { useState } from 'react'
import {retrieveHelloWorldBeanPath} from './api/HelloWorldApiService'
import { Typewriter } from 'react-simple-typewriter'
import {
    useParams,
    Link
  } from "react-router-dom";

export default function WelcomeComponent() {
    const {username} = useParams()
    const [message,setMessage]=useState('')

    const handleClick=()=>{
        // console.log("Clicked..")
        // axios.get('http://localhost:8080/hello')
        //     .then((response)=>successfulResponse(response))
        //     .catch((error)=>errorResponse(error))
        //     .finally(()=>console.log("cleanup"))


        // retrieveHelloWorldBean()
        //     .then((response)=>successfulResponse(response))
        //     .catch((error)=>errorResponse(error))
        //     .finally(()=>console.log("cleanup"))

        retrieveHelloWorldBeanPath("Test")
            .then((response)=>successfulResponse(response))
            .catch((error)=>errorResponse(error))
            // .finally(()=>console.log("cleanup"))
    }
    function successfulResponse(response){
        // console.log(response);
        setMessage(response.data.message);
    }
    function errorResponse(error) {
        console.log(error)
    }


    return(
        <div className="WelcomeComponent container">
            <h1 className="container text-center my-5">Welcome {username}</h1>

            <div className="container text-center my-5" style={{height:"50px", fontSize:'30px'}}><Typewriter
            loop={true}
            
            words={['Taskify - Your Personal Todo Manager', 'Never forget a task again. Let us help you manage your todos.', 'Designed and developed by Anant ']}
            cursor
            cursorStyle='_'
            /></div>
            <div className="container text-center my-3"><h5>Manage Your Todos - <Link to="/todos">Click Here</Link></h5>
            
           
            </div>
           <div className="text-info">
            {message}
           </div>
            
        </div>
    )
}
