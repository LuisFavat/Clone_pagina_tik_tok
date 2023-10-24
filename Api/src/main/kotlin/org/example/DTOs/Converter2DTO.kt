package org.example.DTOs

import model.Comment
import model.Post
import model.SearchResult
import model.User

class Converter2DTO {
    fun toUserDTO(user: User) : UserDTO
    {
        val id        = user.id
        val username  = user.username
        val email     = user.email
        val image     = user.image
        val followers  : List<SimpleUserDTO> =  user.followers.map{toSimpleUserDTO(it)}
        val following  : List<SimpleUserDTO> =  user.following.map{toSimpleUserDTO(it)}
        val posts     = user.posts.map { toSimplePostDTO(it) }

        return UserDTO(id,username,email,image,followers, following, posts)
    }

    fun toSimpleUserDTO(user : User) : SimpleUserDTO
    {
        val id        = user.id
        val username  = user.username
        val image     = user.image

        return SimpleUserDTO(id,username,image)
    }

    fun toSimplePostDTO(post: Post): SimplePostDTO
    {
        val id = post.id
        val title = post.title
        val description = post.description
        val video = post.video
        val likes :List<SimpleUserDTO> = post.likes.map{toSimpleUserDTO(it)}

        return SimplePostDTO(id, title, description, video, likes)
    }

    fun toPostDTO (post: Post):PostDTO
    {
        val id = post.id
        val title = post.title
        val description = post.description
        val video = post.video
        val likes :List<SimpleUserDTO> = post.likes.map{toSimpleUserDTO(it)}
        val comments:List<CommentDTO> = post.comments.map {toCommentDTO(it)}


        return PostDTO(id,title,description,video,likes, comments)
    }

    fun toCommentDTO(comment: Comment):CommentDTO
    {
        val id   = comment.id
        val text = comment.text
        val userDTO = toSimpleUserDTO(comment.user)
        return CommentDTO(id, text, userDTO)
    }

    fun toSearchResultDTO(search: SearchResult): SearchResultDTO{
        val usersDTO = mutableListOf<SimpleUserDTO>()
        val postDTO = mutableListOf<PostDTO>()
        for (i in search.users){
            usersDTO.add(toSimpleUserDTO(i))
        }
        for (i in search.posts){
            postDTO.add(toPostDTO(i))
        }
        return SearchResultDTO(usersDTO,postDTO)
    }

    fun postListToDTO(posts: List<Post>): MutableList<PostDTO> {
        val postDTO = mutableListOf<PostDTO>()
        for (i in posts){
            postDTO.add(toPostDTO(i))
        }
        return postDTO
    }

    fun postListToSimpleDTO(posts: List<Post>): MutableList<SimplePostDTO> {
        val postDTO = mutableListOf<SimplePostDTO>()
        for (i in posts){
            postDTO.add(toSimplePostDTO(i))
        }
        return postDTO
    }

    fun toListOfSimpleUserDTO(user: List<User>): List<SimpleUserDTO>{
        return user.map { toSimpleUserDTO(it) }
    }

}