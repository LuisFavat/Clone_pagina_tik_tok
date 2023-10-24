import React, {useState, useEffect} from "react";
import { useParams } from "react-router-dom";
import Post from "./Post";
import Api from "../../APIs/API_tiktok";


const PostFather = () => {


    let {id} = useParams()

    const [aPost, setAPost] = useState([])

    useEffect(()=> {
        Api.getAPost(id)
        .then(function(data){
            setAPost([data])
        })
    },[])
    


    return(
        <div>
            {aPost.map( element => 
            <Post 
                key = {id}
                id = {id}
                comments={element.comments} 
                title={element.title}
                description={element.description}
                videoSource={element.video}
                numbersOfLikes={element.likes.length} 
                numbresOfComments={element.comments.length}
                onComment={setAPost}
                />)}
        </div>
    )
}

export default PostFather