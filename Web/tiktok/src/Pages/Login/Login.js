import React, {useState} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Api from "../../APIs/API_tiktok";
import './Login.css';


const Login = () => {

    const [user, setUser] = useState()
    const [pass, setPass] = useState()
    const userCapture    = (event) => {
        setLogError("")
        setUser(event.target.value)}
    const passwordCapture = (event) => {
        setLogError("")
        setPass(event.target.value)}
    const navigate = useNavigate()
    const [logError, setLogError] = useState("")
    const location = useLocation()

    const tryToLoging = () => (
        Api.login({username: user, password: pass})
            .then ((data) => {
                if(location.state !== null)
                {
                    navigate(location.state.path)
                }
                else
                {
                    navigate("/home")
                }
                
            })
        .catch((error) => {
           setLogError(error.message)
        })
    )

    return (

        <div className="LoginWrapper">
            <div className="Login">
                Tiktok login
                <div className="FormWrapper">
                  <div className="UserAndPassword">User</div>
                  <input className="LoginInput" onChange={userCapture}></input>
                  <div className="UserAndPassword">Password</div>
                  <input className="LoginInput" type="password" onChange={passwordCapture}></input>
                </div>
                <div  className="LoginValidation">{logError}</div>
                <div className="AccessWrapper">
                  <div className="Access" onClick={tryToLoging}>
                    Access
                  </div>
                </div>
            </div>
            
        </div>
    )

}

export default Login;