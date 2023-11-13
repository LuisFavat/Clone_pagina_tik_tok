import React, {useEffect, useState} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import './NavBar.css';
import Avatar from "../Post/Avatar/Avatar.js"

const NavBar = () => {

   const [logged, setLogged] = useState(false)

   useEffect(() => {
      if(localStorage.getItem("token"))
      {
         setLogged(true)
      }
   },[])


   const [input, setInput] = useState([])
   const inputCapture = (event) => { setInput(event.target.value)}
   
   const search = () => {
      if(input)
      {
         goToSearch()
      }
   }

   const enterEvent = (event) => {
      if (event.key === "Enter")
      {
         if(input)
         {
            goToSearch()
         }
      }
   }

   const location = useLocation()
   const navigate = useNavigate();
   const goToLogin = () => navigate("/login", {state:{path: location.pathname + location.search}})
   const goToSearch = () => navigate(`/search?query=${input}`)       
   const goToHome  = () => navigate("/home")
   const logout = () => {
      localStorage.removeItem("token")
      localStorage.removeItem("id")
      localStorage.removeItem("image")
      localStorage.removeItem("following")
      setLogged(false)
      navigate("/closedsession")
   }
   
   
   return (
      <div>
      <div className = "Nav_background">
      <div className = "TikTok_button" onClick={goToHome}>
         TikTok  
      </div>
      
      <div className = "Search_container">
        
        <div className = "Search_button" onClick={search}>
            S
        </div>
        <span>
           <input  className="Input" onChange={inputCapture} onKeyPress={enterEvent} type = "text" placeholder="Find a video..."></input>
        </span>
      </div>
   
      { logged?
         <div className="NavBarProfile">      
            <Avatar aSingleUser={{id:localStorage.getItem("id"), username:"", image:localStorage.getItem("image")}}/>
            <div className="Logout" onClick={logout}> Log out</div>
         </div>
         : <div className = "Loggin" onClick={goToLogin}> Log in </div> 
      }

   </div>
      <div className="Pusher"></div>
   </div>
   )

}

export default NavBar;