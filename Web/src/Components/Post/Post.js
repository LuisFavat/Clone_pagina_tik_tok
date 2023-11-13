import React, {useState, useEffect} from "react";
import './Post.css';
import Like from "../LastestPost/LastPost/Likes";
import Comment from "./Comments/Comment";
import commentsIco from "./Icons/comments.png"
import NavBar from "../NavBar/NavBar";
import Api from "../../APIs/API_tiktok";
import { useNavigate } from "react-router-dom";


const Post = ({id, comments, title, description, videoSource, numbersOfLikes, numbresOfComments, onComment}) => {
    
    const [islogged, setIsLogged] = useState(false)
    const none = ""

    useEffect(() => {
        if(localStorage.getItem("token"))
        {
            setIsLogged(true)
        }
    },[])

    const [input, setInput] = useState([])
    const inputCapture = (event) => { setInput(event.target.value)}

    const postComment = () => {
        if(input !== "")
        {
            Api.comment(id, input)
            .then( (post) => {
                setInput("")
                onComment([post])
                clearInput()
            })
        }}

    const enterEvent = (event) => {
        if (event.key === "Enter")
        {
           postComment()
        }
    }

    const clearInput = () => {
        const input = document.getElementById("commentInput")
        input.value = ""
    }

    const navigate = useNavigate()
    const goToHome = () => navigate("/home")
       
    



    return ( 
        <>
        <NavBar/>
        <div className="MetaWrapper">
            <div className="Wrapper">
                <button className="GoBack" onClick={goToHome}>X</button>
                <video className="Video" controls> <source src= {videoSource}></source></video>
                <div className="SideContent">
                    <div className="TitleAndDescriptionWrapper">
                        <div className="VerticalDivision">
                            <div className="VideoHeadear">
                                <div className="Title">
                                    {title}
                                </div>
                                <div className="Description">
                                    {description}
                                </div>
                            </div>   
                        </div>
                        <div className="NumbersOfLikesAndCommentsWrapper">
                            <Like id={id} initalLikes ={ numbersOfLikes } />
                            <img className="HomeLikesIco" src = {commentsIco} alt= "comment-ico"></img>
                            <div className="LastPostLikes">
                                {numbresOfComments}
                            </div>
                        </div>  
                        <div className="CommentsWrapperOnPost">
                            <div className="Comments">
                                {comments.map(comment => <Comment key={comment.id} aComment={comment}/>) }
                            </div>
                        </div>
                        {islogged? 
                            <div className="ComentBoxWrapper">                            
                                    <div>Comment</div>
                                <div className="InputCommentWrapper">
                                    <input className="InputComment" id="commentInput" onChange={inputCapture} onKeyPress={enterEvent} defaultValue={none} placeholder="Add a comment..."/>
                                    <div className="PostButton" onClick={postComment}>Post</div>
                                </div>
                            </div> 
                            :
                            <div className="LoginOnPost">
                                Please log in to comment
                            </div>
                        }
                    </div>
                </div>
            </div>
        </div>
        </>  
    )
}

export default Post;