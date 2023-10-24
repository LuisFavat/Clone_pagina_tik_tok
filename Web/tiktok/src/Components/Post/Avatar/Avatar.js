import { useNavigate } from "react-router-dom";
import './Avatar.css';


const Avatar = ({aSingleUser}) => {

    const id = aSingleUser.id
    const username = aSingleUser.username 
    const image    = aSingleUser.image
    
    const navigate = useNavigate()
    const goToUser = () =>  navigate(`/user/:${id}`)

    return (
        <div className="AvatarWrapper" key={id} onClick={goToUser}>
                <img className="Avatar" src = {image}></img>
                <div className="Username">{username}</div>
        </div>
       
    )

}

export default Avatar;