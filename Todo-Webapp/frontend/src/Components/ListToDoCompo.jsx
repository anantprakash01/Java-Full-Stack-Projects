import React, { useEffect, useState } from 'react'
import { deleteById, retrieveAllTodosForUsername } from './api/TodosApiService'
import { useAuth } from './security/AuthContext'
import { useNavigate } from 'react-router-dom'
import Alert from './Alert'

export default function ListToDoCompo(props) {
    const today = new Date()
    // const targetDate = new Date(today.getFullYear()+12, today.getMonth(),today.getDay())

    const AuthContext = useAuth()

    const username = AuthContext.username;


    const[todos,setTodos] = useState([])
    const[message,setMessage]= useState(null)
    const[alert, setAlert] =useState(null)

    const navigate = useNavigate()

    // const todos = [
    //     {id: 1, description: 'Learn AWS', done: false, targetDate:targetDate},
    //     {id: 2, description: 'Learn Full Stack', done: false, targetDate:targetDate},
    //     {id: 3, description: 'Learn DevOps', done: false, targetDate:targetDate},
    // ]

    useEffect(()=>refreshTodos(),[])

    function refreshTodos(){
        retrieveAllTodosForUsername(username)
            .then(response=>{
                    // console.log(response.data)
                    setTodos(response.data)
                }

                )
            .catch(error=>console.log(error))
    }
   
    const handleDelete = (id) =>{
        console.log("Deleted "+id)

        deleteById(username,id)
            .then(
                ()=>{
                
                handleAlert("deleted","success")
                refreshTodos()
            }
            )
            .catch(error=>console.log(error))
    }

    const handleUpdate = (id) =>{
        navigate(`/todo/${id}`)
    }

    const handleAddTodo=()=>{
        navigate(`/todo/-1`)
    }

    const handleAlert=(message,type)=>{
        setAlert({
            msg: message,
            type:type
        })
        setTimeout(()=>{
            setAlert(null)
        },1500)
    }


  return (
    <>
        <Alert alert={alert}
        />
    <div className="ListCompo container">
        
      <h1 className='my-3'>Things You Want To Do!</h1>
      {/* {alert && <div className='alert alert-warning'>{alert}</div>} */}
      
      

      <table className="table">
        <thead>
            <tr>
                <th>Description</th>
                <th>Is Done?</th>
                <th>Target Date</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            {
                todos.map(
                    todo =>(
                        <tr key={todo.id}>
                            <td>{todo.description}</td>
                            <td>{todo.done.toString()}</td>
                            {/* <td>{todo.targetDate.toDateString()}</td> */}
                            <td>{todo.targetDate.toString()}</td>
                            <td><button type="button" className="btn btn-warning" 
                                        onClick={()=>handleUpdate(todo.id)}>Update</button></td>
                            <td><button type="button" className="btn btn-danger" 
                                        onClick={()=>handleDelete(todo.id)}>Delete</button></td>
                           
                        </tr>
                    )
                )
            }
        </tbody>
      </table>
      <div className="container text-center">
      <button className="btn btn-success" onClick={handleAddTodo}>Add Todo</button>
      </div>
    </div>
    </>
  )
}
