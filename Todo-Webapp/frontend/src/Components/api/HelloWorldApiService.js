import {apiClient} from './ApiClient'


// export default function retrieveHelloWorldBean() {
//   return axios.get('http://localhost:8080/hello-world')
// }



export const retrieveHelloWorldBean=() => apiClient.get('/hello-world')

export const retrieveHelloWorldBeanPath=(username) => apiClient.get(`/hello-world/path-variable/${username}`
    // ,{
    // headers: {
    //     Authorization: "Basic QW5hbnQ6ZHVtbXk="
    // }
    // }
)


