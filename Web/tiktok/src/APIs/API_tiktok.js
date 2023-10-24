import axios from 'axios';
import { useNavigate } from 'react-router-dom';

let Axios = axios.create({
    baseURL: 'http://localhost:7070',
    timeout: 1000,
    headers: {'Authorization': localStorage.getItem("token")}
})

const get = (url) => (
        Axios.get(url, {headers: {'Authorization': localStorage.getItem("token")}})
        .then(({data}) =>  data)
        .catch((error) =>{
            const navigate = useNavigate()
            if(error.response.data.message)
            {
                navigate("/error", {state:{message: error.response.data.message}})
            }
            else
            {
                navigate("/error", {state:{message: "Opps!!!"}})
            }
        })
);

const post = (url, aData) => (
    Axios.post(url, aData)
    .then(({data}) =>  data)
    .catch((error) =>{
        const navigate = useNavigate()
        if(error.response.data.message)
        {
            navigate("/error", {state:{message: error.response.data.message}})
        }
        else
        {
            navigate("/error", {state:{message: "Opps!!!"}})
        }
    })
    );

const put = (url) => (
     Axios.put(url)
     .then(({data}) =>  data)
     .catch((error) =>{
        const navigate = useNavigate()
        if(error.response.data.message)
        {
            navigate("/error", {state:{message: error.response.data.message}})
        }
        else
        {
            navigate("/error", {state:{message: "Opps!!!"}})
        }
    })
)


const putLike = (id) => put(`/post/${id}/like`)

const lastestPosts = () =>  get('/latestPosts')

const getAPost = (id) => get(`/post/${id}`)

const getAUser = (id) => get(`/user/${id}`)

const getRecAccount = () => get(`/recommendAccounts`)

const follow = (id) => put(`/user/${id}/follow`)
        
const getUser = () => get('/user')  

const timeline = () => get('/timeline')
    
const search = (param) => get(`/search?query=${param}`)

const login = (data) => (
    Axios.post('/login', data)
        .then((response) => {
            localStorage.setItem("token", response.headers.authorization)
            localStorage.setItem("id", response.data.id)
            localStorage.setItem("image", response.data.image)
            localStorage.setItem("following", JSON.stringify(response.data.following))
            return response.data
        })
        .catch(error => {
            return Promise.reject(error.response.data)   
        }))


const getTrends = () =>  get("/trends")

const getTrendsWithName = (trendName) => get(`/trends/${trendName}`)

const comment = (id, aComment) => (
    post(`/post/${id}/comment`, {"text": aComment})
        .then((reponse) => reponse)
        .catch(error => Promise.reject(error.response.data))
)

const Api = {
    lastestPosts,
    timeline,
    putLike,
    search,
    getAPost,
    login,
    getUser,
    getTrends,
    getTrendsWithName,
    getAUser,
    getRecAccount,
    follow,
    comment
}

export default Api;