import React, { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import Api from "../../APIs/API_tiktok";
import NavBar from "../../Components/NavBar/NavBar";
import LastPost from "../../Components/LastestPost/LastPost/LastPost";
import "./Search.css"
import Avatar from "../../Components/Post/Avatar/Avatar";


const Search = () => {

    const [users, setUsers] = useState([])
    const [posts, setPost] = useState([])
    const [params] = useSearchParams();
   
    useEffect(() => {
        Api.search(params.get("query"))
                .then((data) => { 
                    setUsers(data.users)
                    setPost(data.posts)
                })     

    }, [params])



    return (
        <>
            <NavBar/>
            <div className="SearchWrapper">
                <div className="Users">
                    {users.map(aUser => <Avatar key ={aUser.id} aSingleUser={aUser}/>)}

                </div>
                <div className="SearchPostsResult">
                    {posts.map(aPost => <LastPost key={aPost.id} id={aPost.id} videoUrl={aPost.video} likes={aPost.likes.length} styleClass={"LastPostVideo"}/>)}
                </div>
            </div>
        </>
    )
}

export default Search;