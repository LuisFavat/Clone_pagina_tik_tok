package org.example.DTOs

class UserLoginDTO(val username: String, val password: String)
class SimplePostDTO(val id: String, title:String, description:String, val video:String, val likes: List<SimpleUserDTO>)
class SimpleUserDTO (val id: String, val username: String, val image:String)
class UserDTO(val id: String, username: String, val email:String, val image: String, val followers:List<SimpleUserDTO>, val following:List<SimpleUserDTO>, val posts:List<SimplePostDTO>)
class ErrorDTO(val message:String)
class CommentDTO(val id:String,val text:String, val user:SimpleUserDTO)
class PostDTO(val id:String,val title: String,val description: String, val video:String, val likes:List<SimpleUserDTO>, val comments: List<CommentDTO>)
class NewCommentDTO(val text: String)
class SearchResultDTO(val users:List<SimpleUserDTO>, val posts:List<PostDTO>)