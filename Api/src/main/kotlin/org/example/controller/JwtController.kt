package org.example.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import io.javalin.core.security.RouteRole
import io.javalin.http.Context
import io.javalin.http.Handler
import javalinjwt.JWTGenerator
import javalinjwt.JWTProvider
import model.User
import org.example.DTOs.ErrorDTO
import org.example.Roles

class UserGenerator : JWTGenerator<User>{
    override fun generate(user: User, algorithm: Algorithm): String  {
        val token: JWTCreator.Builder = JWT.create()
            .withClaim("id", user.id)
        return token.sign(algorithm)
    }

}

class JwtController (val service: ServiceModel) {
    private val algorithm = Algorithm.HMAC256("very_secret_kjh5wkjl65ñkjwh5l4wkjh5")
    private val verifier = JWT.require(algorithm).build()
    private val generator = UserGenerator()
    private val provider = JWTProvider(algorithm, generator, verifier)
    val header = "Authorization"

    fun userToToken(user: User): String {
        return provider.generateToken(user)
    }


    fun tokenToId(token: String): String {
        val validateToken = provider.validateToken(token)
        return validateToken.get().getClaim("id").asString()
    }

    fun validate(handler: Handler, ctx: Context, permittedRoles: Set<RouteRole>) {
        val header = ctx.header(header)
        when {
            permittedRoles.contains(Roles.ANYONE) -> handler.handle(ctx)
            header == null -> {
                ctx.status(401)
                ctx.json(ErrorDTO("Not logged. Empty header"))
                return
            }

            else -> {
                val token = provider.validateToken(header)
                if (token.isPresent) {
                    val userId = token.get().getClaim("id").asString()
                    val user = try {
                        service.getUser(userId)
                    }catch(e:Exception)
                    {
                        ctx.status(403)
                        ctx.json(ErrorDTO("Invalid token"))
                        return
                    }
                    if (permittedRoles.contains(Roles.USER)) {
                        ctx.attribute("userId", userId)
                        ctx.attribute("user", user)
                        handler.handle(ctx)
                    } else {
                        ctx.status(403)
                        ctx.json(ErrorDTO("Not allowed, you must login. Insufficient permissions"))
                        return
                    }

                }  else {
                    ctx.status(401)
                    ctx.json(ErrorDTO("Not logged. Empty header, no token found"))
                    return
                }
            }
        }
    }
}

