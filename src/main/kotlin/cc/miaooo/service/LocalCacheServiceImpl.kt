package cc.miaooo.service

import jakarta.enterprise.context.ApplicationScoped
import java.util.concurrent.ConcurrentHashMap

@ApplicationScoped
class LocalCacheServiceImpl : LocalCacheService {
    val map: ConcurrentHashMap<String, String> = ConcurrentHashMap()

    override fun get(key: String): String {
        return map.getOrDefault(key, "");
    }

    override fun put(key: String, value: String) {
        map.put(key, value)
    }
}