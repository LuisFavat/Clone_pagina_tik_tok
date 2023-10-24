import React, {useState, useEffect} from "react";
import Api from "../../../APIs/API_tiktok"
import LastPost from "../LastPost/LastPost";
import './Timeline.css'

const Timeline = () => {
   
    const [timeline, setTimeline] = useState([])

    useEffect(() => {
        if(localStorage.getItem("token"))
        {
            Api.timeline()
            .then(function (data){
                setTimeline(data)
            })     

        }
    },[])


    return (
        <>
            {timeline.length !== 0?  
                <div className="RecommendAcountsWrapper">
                    <div className="RecommendAcountsTitle">
                        Timeline
                    </div>
                    <div>
                        {timeline.map(element => <LastPost key={element.id} id ={element.id} videoUrl={element.video} likes={element.likes.length} styleClass={"TimelineVideo"} />)}
                        
                    </div>
                </div>
                
                : <div></div>}
        </>

  


)}

export default Timeline;