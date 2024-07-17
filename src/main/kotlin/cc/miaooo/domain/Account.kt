package cc.miaooo.domain


data class Account(
    val id: Long? = null,
    val name: String = "",
    val pwd: String = "",
    val phone: String = "",
    val email: String = ""
) {
    /*    fun loginByPwd(req: AccountLoginReq): Account? {
            val firstResult = find<Account>("name", name).singleResult<Account>()
                ?: throw CommonException("用户不存在")
            if (firstResult.pwd != req.pwd) {
                throw CommonException("密码有误")
            }
            return firstResult;
        }*/
}