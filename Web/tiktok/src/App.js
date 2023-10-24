
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './Pages/Home/Home.js';
import Search from './Pages/Search/Search.js';
import PostFather from './Components/Post/PostFather.js';
import User from './Pages/User/User.js';
import Login from './Pages/Login/Login.js';
import ClosedSession from './Pages/ClosedSession/ClosedSession.js'
import TrendsPage from './Pages/Trends/TrendsPage';
import Error from './Pages/Error/Error.js';


function App() {

  return (
    
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/home" element={<Home />}></Route>
        <Route path="/post/:id" element = {<PostFather/>}></Route>
        <Route path={"/search"} element={<Search />}></Route>
        <Route path={"/user/:id"} element={<User />}></Route>
        <Route path={"/closedsession"} element={<ClosedSession />}></Route>
        <Route path={"/trend/:trendName"} element={<TrendsPage />}></Route>
        <Route path={"/:url/*"} element={<Error/>}></Route>
      </Routes>
    </BrowserRouter>
      
  )

}

export default App;
