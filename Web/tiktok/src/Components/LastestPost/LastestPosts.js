import React, {useState, useEffect} from "react";
import Api from "../../APIs/API_tiktok";
import './LastestPosts.css';
import Trends from "../Trends/TrendsList.js"
import RecommendAcounts from "../RecommendAccounts/RecommendAccounts.js"
import LastPost from "./LastPost/LastPost";
import Timeline from "./Timeline/Timeline";


const HomeContent = () => {

    const [lastestPosts, setLastestPosts] = useState([]);

    useEffect(() => {
        Api.lastestPosts()
                .then(function (data){
                    setLastestPosts(data)

                })     

    }, [])


    return (
        <div className="HomeContent">
            <div className="HomeSideBar">
                <div className="InsideHomeSideBarWrapper">
                <RecommendAcounts/>
                <Trends/>
                <Timeline/>
                </div>
            </div>
            <div className="LastestPosts">
                {lastestPosts.map(element => <LastPost key={element.id} id ={element.id} videoUrl={element.video} likes={element.likes.length} styleClass={"LastPostVideo"} />)}
            </div>
        </div>


)}

export default HomeContent;




