import { useNavigate } from "react-router-dom";
import Like from "./Likes";
import "../LastestPosts.css"

const LastPost = ({id, videoUrl, likes, styleClass}) => {
  
    const navigate = useNavigate();

    const goToPost = () => {
        navigate(`/post/${id}`)
    }

    return (
    <div className="LastPostWrapper">
        <video className={styleClass} controls onClick={goToPost}>
            <source src={videoUrl}/>
        </video>
        <Like id ={id} initalLikes = { likes }/>  
    </div>
)}

export default LastPost;