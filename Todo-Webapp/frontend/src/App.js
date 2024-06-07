// import logo from './logo.svg';
import './App.css';
import './Style/style.css';
import NavBar from './Components/NavBar';
import LoginComponent from './Components/LoginComponent';
import WelcomeComponent from './Components/WelcomeComponent';
import ListToDoCompo from './Components/ListToDoCompo';
import LogoutComponent from './Components/LogoutComponent';
import ErrorComponent from './Components/ErrorComponent';
import FooterComponent from './Components/FooterComponent';
import AuthProvider,{useAuth} from './Components/security/AuthContext'
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,

} from "react-router-dom";
import TodoCompo from './Components/TodoCompo';
import CreateAcc from './Components/CreateAcc';


function AuthenticatedRoute({children}){
  const authContext = useAuth()
  if(authContext.isAuthenticated)
      return children
    return <Navigate to="/" />
}
function App(props) {
  return (
    <>
    <AuthProvider>
      <Router>
        <NavBar/>
            <Routes>
            <Route path="" element={<LoginComponent/>}></Route>
            <Route path="/login" element={<LoginComponent/>}></Route>
            <Route path="/create_acc" element={<CreateAcc/>}></Route>
            <Route path="/welcome/:username" element={
                <AuthenticatedRoute>
                  <WelcomeComponent/>
                </AuthenticatedRoute>
            }></Route>

            <Route path="/todos" element={
                  <AuthenticatedRoute>
                    <ListToDoCompo/>
                  </AuthenticatedRoute>
            }></Route>

            <Route path="/todo/:id" element={
                  <AuthenticatedRoute>
                    <TodoCompo alert={props.alert}/>
                  </AuthenticatedRoute>
            }></Route>

            <Route path="/logout" element={
                <AuthenticatedRoute>
                  <LogoutComponent/>
                </AuthenticatedRoute>
            }></Route>

            <Route path="*" element={<ErrorComponent/>}></Route>
            </Routes>
            <FooterComponent/>
        </Router>
    </AuthProvider>
    </>
  );
}

export default App;
