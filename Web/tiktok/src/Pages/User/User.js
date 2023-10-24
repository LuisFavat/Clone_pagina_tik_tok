import NavBar from "../../Components/NavBar/NavBar.js"
import "./User.css"
import Avatar from "../../Components/Post/Avatar/Avatar.js"
import { useEffect, useState } from "react"
import Api from "../../APIs/API_tiktok.js"
import { useParams } from "react-router-dom"
import LastPost from "../../Components/LastestPost/LastPost/LastPost.js"

const User = ()=>
{
    let {id} =  useParams()
    let userID = id.slice(1)
    const [user, setUser] = useState(null)

    const [followStatus, setFollowStatus] = useState("")

    const determineFollowState = () => {
        if(localStorage.getItem("token") === null)
        {
            setFollowStatus("Follow")
        }
        else
        {
            let following = JSON.parse(localStorage.getItem("following"))
            if(following.find(element => element.id == userID))
            {
                setFollowStatus("Not follow")
            }
            else{
                 if(localStorage.getItem("id") === userID)
                 {
                     setFollowStatus("(｡●́‿●̀｡)")
                 }
                 else
                 {
                     setFollowStatus("Follow")
                 }          
            }

        }

    }

    useEffect(() => {
        Api.getAUser(userID)
            .then((user) => {
                setUser(user)
                determineFollowState()
  
            })
    },[id])

    const Followxxx = ({title, followxxx}) => {
        return(
            <div className="UserPageFollowxxxWrapper">
            {title}
                <div className="UserPageFollowxxx">
                    <div className="UserPageFollowxxxCarousel">
                        {followxxx.map(follower => <div key={follower.id} className="UserPageAvatarWrapper"><Avatar  aSingleUser={follower} /></div>)}
                    </div>
                </div>
            </div>
        )
    }

    const followAction = () => {
        if( localStorage.getItem("id") !== userID) 
        {
            Api.follow(userID)
                .then((data) => {
                    localStorage.setItem("following", JSON.stringify(data.following))
                    determineFollowState()})
        }
    }

    return(
        <>
            <NavBar/>
            {user===null? <></> : 
            <div className="UserPageWrapper">
                <div className="UserPageSideBar">
                    <div className="UserPageProfile">
                        Profile
                        <div className="UserData">
                            <Avatar key={user.id} aSingleUser={{id:user.id, username: user.email, image: user.image}}/>
                            <div className="UserPageFollowButtonWrapper">
                                <div className="UserPageFollowButton" onClick={followAction}>
                                    {followStatus}
                                </div>
                            </div>
                        </div>
                    </div>
                    <Followxxx  title={"Followers"}  followxxx={user.followers} />
                    <Followxxx  title={"Following"}   followxxx={user.following} />
                </div>
                <div className="UserPagePosts">
                        {user.posts.map(element => <div key={element.id}  className="UserPageLastPostWrapper"><LastPost id ={element.id} videoUrl={element.video} likes={element.likes.length} styleClass={"TimelineVideo"}/></div>)}                 
                </div>
            </div>
            }
        </>
    )
}

export default User;