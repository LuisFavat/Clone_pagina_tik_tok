package org.example.service

import bootstrap.Bootstrap
import drafts.*
import model.Post
import model.SearchResult
import model.User

class ServiceModel {

    private val system =  Bootstrap().getSystem()

    fun login(username:String, password:String): User
    {
        return system.login(username, password)
    }

    fun getUser(userId: String): User
    {
        return system.getUser(userId)
    }

    fun getPost(postId: String): Post
    {
        return  system.getPost(postId)
    }

    fun getLatestPosts():List<Post>{
        return system.getLatestPosts()
    }

    fun upDateFollower(userId: String, followerId: String): User {
        return system.updateFollow(userId, followerId)
    }

    fun search(text : String): SearchResult{
        return system.search(text)
    }

    fun getTopTrends():List<String>{
        return system.getTopTrends()
    }

    fun getTrend(trendName : String):List<Post>{
        return system.getTrend(trendName)
    }

    fun addComment(idUser: String, idPost: String, draftComment: DraftComment): Post{
        return system.addComment(idUser,idPost,draftComment)
    }

    fun addOrRemoveLike(idUser: String, idPost: String) : Post
    {
        return system.updateLike(idUser, idPost)
    }

    fun getTimeline(idUser: String) : List<Post>{
        return system.getTimeline(idUser)
    }

    fun getRecommendAccounts(idUser: String): List<User>{
        return system.getRecommendAccounts(idUser)
    }

    fun getRecommendAccounts(): List<User>{
        return system.getRecommendAccounts()
    }
}