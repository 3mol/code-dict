package cc.miaooo.service

import cc.miaooo.application.req.AccountLoginReq
import cc.miaooo.domain.Account

interface AccountService {
    fun detail(id: Long): Account
    fun register(account: Account): Account
    fun login(req: AccountLoginReq): Account
}
