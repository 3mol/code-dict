package cc.miaooo.application

import cc.miaooo.application.req.AccountLoginReq
import cc.miaooo.domain.Account
import cc.miaooo.service.AccountService
import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
class AccountResource(
    val accountService: AccountService
) {

    @GET
    @RolesAllowed("admin", "user")
    @Path("/{id}")
    fun detail(@PathParam("id") id: Long): Account {
        return accountService.detail(id)
    }

    @POST
    @PermitAll
    @Transactional
    fun register(@RequestBody account: Account): Account {
        // creating a person
        return accountService.register(account);
    }

    @POST
    @PermitAll
    @Path("/login")
    fun login(@RequestBody req: AccountLoginReq): Account {
        // creating a person
        return accountService.login(req);
    }
}