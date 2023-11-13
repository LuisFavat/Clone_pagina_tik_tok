import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./ClosedSession.css"

const ClosedSession = () => {

    const navigate = useNavigate()
    useEffect(()=>{
        setTimeout(()=> {navigate("/home")},2000)
    },[])
    

    return(
        <div className="ClosedSessionWrapper">
            <div className="ClosedSession">
                <div>You have successfully logged out</div>
                <div>Come back soon!!!</div>
                <div>You will be redirected to home in a moment.</div>
            </div>
        </div>
    )
}


export default ClosedSession;