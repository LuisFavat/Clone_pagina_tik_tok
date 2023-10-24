import Trend from "../../Pages/Trends/Trend"
import { useEffect, useState } from "react";
import Api from "../../APIs/API_tiktok";
import { useParams } from "react-router-dom";
import Button from "../Button/Button";
import "../../Pages/Trends/Trends.css"

const Trends = ({posts}) => {

    const [trendsOnPage, setTrendsOnPage] = useState([])
    const [allTrends, setAllTrends] = useState([])
    const trendsPerPage = 14
    const [lastPage, setLastPage] = useState(0)

 
    useEffect(() => {
            setLastPage(  parseInt(posts.length/trendsPerPage) +1 )
            setTrendsOnPage(posts.slice(0,trendsPerPage))
            setAllTrends(posts)
            setActualPage(0)
    },[posts])

    const [actualPage, setActualPage] = useState(0)
    const previousPage = () => {
        if(actualPage !== 0)
        {
            setActualPage( actualPage - 1 )
            window.scrollTo({
                top: 0,
              })
        }
    }

    const nextPage = () => {
        if(lastPage !== actualPage +1 )
        {
            setActualPage( actualPage + 1 )
            window.scrollTo({
                top: 0,
              })
        }
    }

    useEffect(() => {
        setTrendsOnPage(allTrends.slice( trendsPerPage * actualPage, trendsPerPage * (actualPage + 1)  ))
    },[actualPage])

    return (
            <div className="TrendsWrapper">
                <div className="TrendsGroup">
                    { trendsOnPage.map( trend => <Trend key={trend.id} id={trend.id} title={trend.title} description={trend.description} videoUrl={trend.video} likesCount={trend.likes.length} />) }
                </div>
            <div>
                <div className="TrendsNavButtons">
                    <Button tag={"Back"} accionClick={previousPage} /><Button tag={"Next"} accionClick={nextPage}/>
                </div>
            </div>
            </div>
    )
}

export default Trends;    