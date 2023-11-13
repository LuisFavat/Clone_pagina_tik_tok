import "./TrendsList.css"
import { useNavigate } from "react-router-dom";
import Api from "../../APIs/API_tiktok";
import { useEffect, useState } from 'react';

const Trend = ({aTrend}) => {

    const navigate = useNavigate()
    const goToTrend = () => 
    navigate(`/trend/:${aTrend.slice(1)}`)

    return (
       <div  key={aTrend} className="TrendsListWrapper" onClick={goToTrend}>
            {aTrend}
       </div>
)}

const TrendsList =() =>{
    const [trends, setTrends] = useState([])

useEffect(() => {
    Api.getTrends()
        .then((trends) => setTrends(trends))
},[])
  
    return (
        <div className="MetaTrendsListWrapper">
            <div className="TrendListTitle">
                Trends
            </div>
            <div className="TrendsListAllWrapper">
                {trends.map(trend => <Trend key={trend} aTrend={trend}/>) }
            </div>
        </div>
)}

export default TrendsList;
