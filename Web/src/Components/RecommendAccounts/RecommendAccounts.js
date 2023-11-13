import React, {useState, useEffect} from "react";
import Api from "../../APIs/API_tiktok";
import './RecommendAccounts.css';
import Avatar from "../../Components/Post/Avatar/Avatar"

const RecommendAcounts = () => {

    const [users, setUsers] = useState([])

    useEffect(() => {
        Api.getRecAccount()
                .then((data) => {
                    setUsers(data)
                })     
    },[])


    return (

            <div className="RecommendAcountsWrapper">
                <div className="RecommendAcountsTitle">Recommend Accounts</div>
                <div>
                    {users.map(element => <div className="RecommendAccountsAvatarWrapper" key={element.id}><Avatar  aSingleUser={element}/></div>)}
                </div>
            </div>
            



    )

}



export default RecommendAcounts;