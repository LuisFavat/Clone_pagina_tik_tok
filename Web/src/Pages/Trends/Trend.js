import { useNavigate } from "react-router-dom";
import "./Trends.css"
import Like from "../../Components/LastestPost/LastPost/Likes";

const Trend = ({id, title, description, videoUrl, likesCount}) => {

    const navigate = useNavigate()
    const goToPost   = () => { navigate(`/post/${id}`) }

    return(

        <div className="Trend">
            <div className="TrendTitle">{title}</div>
            <div className="TrendDescription">{description}</div>
            <div className="TrendLikes">
            <Like id={id} initalLikes={likesCount}/>
            </div>
            <video className="VideoTrend" controls onClick={goToPost}>
                    <source src={videoUrl}/>
            </video>
        </div>

    )
}

export default Trend;