package cc.miaooo.service

interface LocalCacheService {
    fun get(key: String): String
    fun put(key: String, value: String): Unit
}
