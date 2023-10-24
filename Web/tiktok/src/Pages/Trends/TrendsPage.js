import { useEffect, useState } from "react";
import NavBar from "../../Components/NavBar/NavBar";
import Trends from "../../Components/Trends/Trends";
import Api from "../../APIs/API_tiktok";
import { useParams } from "react-router-dom";

const TrendsPage = () => {
 
    const {trendName} = useParams()
    const [posts, setPosts] = useState([])

    useEffect(() =>{
        Api.getTrendsWithName(trendName.slice(1))
            .then((trends) => setPosts(trends))
    },[])


    return (
        <>
            <NavBar/>
            <Trends posts={posts}/>
        </>
    )
}

export default TrendsPage;