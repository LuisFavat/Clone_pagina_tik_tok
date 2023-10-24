import exceptions.UserException
import io.javalin.http.Context
import model.User
import org.example.DTOs.Converter2DTO
import org.example.DTOs.ErrorDTO
import org.example.DTOs.UserLoginDTO
import org.example.service.JwtController
import org.example.service.ServiceModel


class UserController(val serviceModel: ServiceModel, val tokenController: JwtController) {

    val converter = Converter2DTO()
    fun login(ctx: Context) {
        val userLogin :UserLoginDTO = try {ctx.bodyValidator<UserLoginDTO>()
            .check({ it.username.isNotEmpty()}, "Username cannot be empty")
            .check({ it.password.isNotEmpty()}, "Password cannot be empty")
            .get()
        }
        catch (e:Exception){
                ctx.status(400)
                ctx.json(ErrorDTO("Username or Password cannot be empty"))
            return
            }


        val user =  try {
            serviceModel.login(userLogin.username,userLogin.password)
        } catch (e: UserException){
            ctx.status(404)
            ctx.json(ErrorDTO("Incorrect user or password"))
            return
        }

        val userDTO = converter.toUserDTO(user)
        ctx.header("Authorization", tokenController.userToToken(user))
        ctx.json(userDTO)
    }

    fun getLoginUser(ctx: Context) {
        val user :User = ctx.attribute("user")!!
        ctx.json(converter.toUserDTO(user))
    }

    fun getUser(ctx: Context) {
        val userId = ctx.pathParam("id")

        val user = try {
            serviceModel.getUser(userId)
        } catch (e: UserException) {
            ctx.status(404)
            ctx.json(ErrorDTO("User not found"))
            return
        }
        ctx.json(converter.toUserDTO(user))
    }


    fun putUserFollower(ctx: Context) {
        val userId :String = ctx.attribute("userId")!!
        val followerId = ctx.pathParam("id")

        val user = try {
            serviceModel.upDateFollower(userId, followerId)
        }catch (e:UserException){
            ctx.status(404)
            ctx.json(ErrorDTO("Follower id not found"))
            return
        }
        ctx.json(converter.toUserDTO(user))
    }

    fun search(ctx: Context) {
        val text = ctx.queryParam("query")
        if (text != null){
            val search = serviceModel.search(text)
            ctx.json(converter.toSearchResultDTO(search))
        } else {
            ctx.status(404)
            ctx.json(ErrorDTO("Not text to search"))
            return
        }
    }

    fun topTrends(ctx: Context) {
        val trends = serviceModel.getTopTrends()
        ctx.json(trends)
    }

    fun getTrend(ctx: Context) {
        val trend = ctx.pathParam("trendName")
        val posts = serviceModel.getTrend(trend)
        ctx.json(converter.postListToDTO(posts))
    }

    fun getTimeline(ctx: Context) {
        val userId :String = ctx.attribute("userId")!!
        val timeline = serviceModel.getTimeline(userId)
        ctx.json(converter.postListToSimpleDTO(timeline))
    }

    fun getRecommendAccounts(ctx: Context){

        val token = ctx.header("Authorization")
        val recommendAccounts : List<User>?

        if(token == null )
        {
            recommendAccounts = serviceModel.getRecommendAccounts()
        }
        else
        {
            recommendAccounts = serviceModel.getRecommendAccounts(tokenController.tokenToId(token))
        }
        ctx.json(converter.toListOfSimpleUserDTO(recommendAccounts))

    }



}