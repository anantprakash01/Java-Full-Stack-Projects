import { createContext, useContext, useState } from "react";
import { executeJwtAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";
// 1: Create a Context
export const AuthContext = createContext()

export const useAuth = ()=> useContext(AuthContext)


// 2: Share the create context with other Components
export default function AuthProvider({children}){
    // Put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)

    const [username, setUsername] = useState(null)
    const [token, setToken] = useState(null)

    // function login(username,password){
    //     if(username==='Anant' && password==='dummy'){
    //         setAuthenticated(true)
    //         setUsername(username)
    //         return true
    //     }else{
    //         setAuthenticated(false)
    //         setUsername(null)
    //         return false
    //     }
    // }

    // async function login(username,password){

    //     const baToken = "Basic "+window.btoa(username +":"+password)

    //     const response =await executeBasicAuthenticationService(baToken)
            
    //     try{
    //         if(response.status==200){
    //             setAuthenticated(true)
    //             setUsername(username)
    //             setToken(baToken)

    //             apiClient.interceptors.request.use(
    //                 (config)=>{
    //                     console.log("interception and adding a token")
    //                     config.headers.Authorization = baToken
    //                     return config 
    //                 }
    //             )

    //             return true
    //         }else{
    //             logout()
    //             return false
    //         }
    //     }catch(error){
    //         logout()
    //         return false
    //     }
    // }


    async function login(username,password){

        
        try{
            const response =await executeJwtAuthenticationService(username,password)
            if(response.status==200){
                const jwtToken = "Bearer " +response.data.token
                setAuthenticated(true)
                setUsername(username)
                setToken(jwtToken)

                apiClient.interceptors.request.use(
                    (config)=>{
                        console.log("interception and adding a token")
                        config.headers.Authorization = jwtToken
                        return config 
                    }
                )

                return true
            }else{
                logout()
                return false
            }
        }catch(error){
            logout()
            return false
        }
    }

    function logout() {
        setAuthenticated(false)
        setToken(null)
        setUsername(null)
      }

    return(
        <AuthContext.Provider value={{isAuthenticated,setAuthenticated,login,logout,username,token}}>
            {children}
        </AuthContext.Provider>
    )
}