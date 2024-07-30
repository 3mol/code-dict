package cc.miaooo.service

import cc.miaooo.application.req.AccountLoginReq
import cc.miaooo.common.CommonException
import cc.miaooo.domain.Account
import cc.miaooo.infra.repository.AccountPo
import cc.miaooo.infra.repository.AccountRepository
import io.quarkus.elytron.security.common.BcryptUtil
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AccountServiceImpl(val accountRepository: AccountRepository) : AccountService {

    override fun detail(id: Long): Account {
        val accountPo: AccountPo = accountRepository.detail(id);
        return Account(id, accountPo.name, accountPo.pwd, accountPo.phone, accountPo.email)
    }

    override fun login(req: AccountLoginReq): Account {
        val accountPo = accountRepository.find("name", req.name).singleResultOptional<AccountPo>()
        val optional = accountPo.map {
            if (it.pwd != BcryptUtil.bcryptHash(req.pwd)) {
                throw CommonException("密码有误")
            }
            return@map Account(
                it.id,
                it.name,
                it.pwd,
                it.phone,
                it.email
            )
        }.orElseThrow {
            CommonException("用户不存在")
        }
        return optional
    }

    override fun register(account: Account): Account {
        val existAccount = accountRepository.find("name", account.name).count()

        when (existAccount) {
            1L -> throw CommonException("用户名已被占用")
        }
        val accountPo =
            AccountPo(
                null,
                account.name,
                // 每次hash的结果都不同，暂时不清楚内部逻辑
                BcryptUtil.bcryptHash(account.pwd),
                "user",
                account.phone,
                account.email
            )
        accountRepository.persist(accountPo)
        return Account(
            accountPo.id,
            accountPo.name,
            accountPo.pwd,
            accountPo.phone,
            accountPo.email
        )
    }
}