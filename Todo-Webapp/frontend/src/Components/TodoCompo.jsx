import React, { useEffect, useState } from 'react'
import { createTodoApi, retrieveTodoApi, updateTodoApi } from './api/TodosApiService'
import {useNavigate, useParams} from "react-router-dom"
import { useAuth } from './security/AuthContext'
import{Formik,Form,Field, ErrorMessage} from 'formik'



export default function TodoCompo() {

    const {id} = useParams()

    const authContext = useAuth();
    const username  = authContext.username;
    const[description, setDescription] =useState('')
    const[targetDate, setTargetDate] =useState('')

    const navigate = useNavigate()

    useEffect(
      ()=>retrieveTodo(),
      [id]
    )

    function retrieveTodo(){

      if(id!==-1){
        retrieveTodoApi(username,id)
            .then(response=>{
              setDescription(response.data.description)
              setTargetDate(response.data.targetDate)
            })
            .catch(error=>console.log(error))
      }
    }

    function onSubmit(values){
      // console.log(values)
      const todo={
          id: id,
          username: username,
          description: values.description,
          targetDate: values.targetDate,
          done: false
      }
      if(id===-1){
        createTodoApi(username,todo)
            .then(response=>{
              navigate("/todos")
            })
            .catch(error=>console.log(error))
      }else{
      updateTodoApi(username,id,todo)
            .then(response=>{
              // console.log(response)
              navigate("/todos")
            })
            .catch(error=>console.log(error))
          }
    }

    function validate(values){
      let errors ={
        // description:"Enter a valid Description",
        // targetDate:"Enter a valid target date"
      }
      if(values.description.length<5)
            errors.description="Enter a valid Description"
      if(values.targetDate == null || new Date(values.targetDate) <= new Date()|| values.targetDate==='')
            errors.description="Enter a valid date"
      // console.log(values)
      return errors
    }


  return (
    <div className="container text-center" >
        <h1>Enter Todo Details</h1>
        <div style={{minHeight:"600px"}}>
          <Formik initialValues={{description,targetDate}}
                    enableReinitialize={true}
                    onSubmit={onSubmit}
                    validate={validate}
                    validateOnChange={false}
                    validateOnBlur={false}
                    >
            {
              (props)=>(
                <Form>
                  <ErrorMessage
                    name="description"
                    component="div"
                    className="alert alert-warning"
                  />
                  <ErrorMessage
                    name="targetDate"
                    component="div"
                    className="alert alert-warning"
                  />
                  <fieldset className="form-group">
                    <label>Description</label>
                    <Field type="text" className="form-control" name="description"></Field>
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Target Date</label>
                    <Field type="date" className="form-control" name="targetDate"></Field>
                  </fieldset>
                  <div>
                    <button className="btn btn-success m-5" type="submit">Save</button>
                  </div>
                </Form>
              )
            }
          </Formik>
        </div>
      
    </div>
  )
}
