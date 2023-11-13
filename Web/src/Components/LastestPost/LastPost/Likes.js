import { useEffect, useState } from "react";
import likeIco from "../../Post/Icons/heart.png"
import Api from "../../../APIs/API_tiktok";
import { useLocation, useNavigate } from "react-router-dom";
import "./Likes.css"

const Like = ({id, initalLikes}) => {




    const [likes, setLikes] = useState(initalLikes)
    useEffect(() => {
        setLikes(initalLikes)

    },[])

    const navigate = useNavigate()
    const actualPath = useLocation()
    const likesHandler = () => {
       if(localStorage.getItem("token"))
       {
            Api.putLike(id)
                .then ((data) => setLikes(data.likes.length))
       }
       else
       {
            navigate("/login", {state:{path: actualPath.pathname}})
       }

    }

    return(
    <>
    <div className="LikeWrapper">
        <img className="HomeLikesIco" src = {likeIco} alt= "heart-ico" onClick={() => likesHandler(id)} ></img>
        <div className="LastPostLikes">
        {likes}
        </div>
    </div>
    </>
    )
}


export default Like;