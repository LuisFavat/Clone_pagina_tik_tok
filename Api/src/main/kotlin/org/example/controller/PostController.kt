package org.example.controller

import drafts.DraftComment
import exceptions.PostException
import io.javalin.http.Context
import org.example.DTOs.Converter2DTO
import org.example.DTOs.ErrorDTO
import org.example.service.ServiceModel
import java.lang.Exception

class PostController (val serviceModel: ServiceModel) {

    val converter = Converter2DTO()

    fun getPost (ctx: Context)
    {
        val postID =ctx.pathParam("id")
        val post = try {
            serviceModel.getPost(postID)
        }catch(e:PostException)
        {
            ctx.status(404)
            ctx.json(ErrorDTO("Not found post id"))
            return
        }
        ctx.json(Converter2DTO().toPostDTO(post))
    }

    fun getLatestPostsC(ctx: Context)
    {
        val posts = serviceModel.getLatestPosts()
        ctx.json(converter.postListToSimpleDTO(posts))
    }

    fun putLikeInAPostC(ctx: Context) {
        val postId = ctx.pathParam("id")
        val userId :String = ctx.attribute("userId")!!
        val post = try {
            serviceModel.addOrRemoveLike(userId,postId)
        }catch (e:PostException ){
            ctx.json(ErrorDTO("Post id not found")).status(404)
            return
        }
        ctx.json(converter.toPostDTO(post))
    }

    fun postComment(ctx: Context)
    {
        val postId = ctx.pathParam("id")
        val userId :String = ctx.attribute("userId")!!
        val draftComment :DraftComment = try {
            ctx.bodyValidator<DraftComment>()
                .check({ it.text.isNotEmpty() }, "Comment can not be empty")
                .get()
        }catch (e:Exception)
        {
            ctx.status(400)
            ctx.json(ErrorDTO("Comment can not be empty"))
            return
        }



        val post  = try {
            serviceModel.addComment(userId, postId, draftComment)
        }catch (e:PostException){
            ctx.json(ErrorDTO("Post id not found")).status(404)
            return
        }

        ctx.json(converter.toPostDTO(post))
    }


}
