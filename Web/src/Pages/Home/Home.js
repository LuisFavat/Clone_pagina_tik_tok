import React from "react";
import NavBar from '../../Components/NavBar/NavBar.js';
import './Home.css';
import HomeContent from "../../Components/LastestPost/LastestPosts.js"




const Home = () => {

    return (
  <div className="Home">
    <NavBar/>
    <HomeContent/>
  </div>
 )}

export default Home;