package org.example

import UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.RouteRole
import org.example.controller.PostController
import org.example.service.JwtController
import org.example.service.ServiceModel

internal enum class Roles : RouteRole {
    ANYONE, USER
}

class Api {
    val service = ServiceModel()
    val jwtController = JwtController(service)
    val userController = UserController(service, jwtController)
    val postController = PostController(service)


    fun start() {

        val app = Javalin.create {
            it.enableCorsForAllOrigins()
            it.defaultContentType = "application/json"
            it.accessManager(jwtController::validate)
        }.start(7070)

        app.before {
            it.header("Access-Control-Expose-Headers", "*")
        }

        app.routes {
            path("/login") {
                post(userController::login, Roles.ANYONE)
            }
            path("/user") {
                get(userController::getLoginUser, Roles.USER)
                path("{id}") {
                    get(userController::getUser, Roles.ANYONE)
                    path("/follow"){
                        put(userController::putUserFollower, Roles.USER)
                    }
                }
            }

            path("post"){
                path("{id}"){
                    get(postController::getPost,Roles.ANYONE)
                    path("comment"){
                        post(postController::postComment, Roles.USER)
                    }
                    path("like"){
                        put(postController::putLikeInAPostC,Roles.USER)
                    }
                }

            }
            path("/latestPosts"){
                get(postController::getLatestPostsC,Roles.ANYONE)
            }

            path("/search"){
                get(userController::search,Roles.ANYONE)
            }

            path("/trends"){
                get(userController::topTrends,Roles.ANYONE)
                path("{trendName}"){
                    get(userController::getTrend,Roles.ANYONE)
                }
            }

            path("/recommendAccounts"){
                get(userController::getRecommendAccounts, Roles.ANYONE)
            }

            path("/timeline"){
                get(userController::getTimeline,Roles.USER)
            }
        }
    }


}


fun main() {
    val api = Api()
    api.start()
}


