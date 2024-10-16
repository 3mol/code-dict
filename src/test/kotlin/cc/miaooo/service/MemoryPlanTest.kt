package cc.miaooo.service

import org.junit.jupiter.api.Test
import java.util.stream.Collectors

class MemoryPlanTest {

    @Test
    fun doPlan() {
        val memoryPlan = MemoryPlan<String>()
        val doPlan = memoryPlan.doPlan(5, ('a'..'z').map { it.toString() }.toList())
        doPlan.forEach({ day ->
            println(day.date)
            println(day.studySomething.stream().map { it.tag +" -> "+ it.studySomething }
                .collect(Collectors.joining("; ")))
        })
    }
}