import React, { useState } from 'react'
import {retrieveHelloWorldBeanPath} from './api/HelloWorldApiService'
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

        retrieveHelloWorldBeanPath("Anant")
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
            <h1 className="container text-center my-5">Welocme {username}</h1>
            <div className="container text-center my-3"><h5>Manage Your Todos - <Link to="/todos">Click Here</Link></h5>
            <br/>
            <button type="button" className="btn btn-success my-3" onClick={handleClick}>Hello</button>
            </div>
           <div className="text-info">
            {message}
           </div>
            
        </div>
    )
}
