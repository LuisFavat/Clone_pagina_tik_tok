import Avatar from "../Avatar/Avatar";
import './Comment.css';



const Comment = ({aComment}) =>{
    return(
        <div className="CommentsWrapper">
            <Avatar aSingleUser={aComment.user} />

            <div className="TextComment">
                {aComment.text}
            </div>
        </div>
    )
}


export default Comment;