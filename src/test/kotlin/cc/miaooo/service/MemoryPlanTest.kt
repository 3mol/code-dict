package cc.miaooo.service

import org.junit.jupiter.api.Test
import java.util.stream.Collectors

class MemoryPlanTest {

    @Test
    fun doPlan() {
        val memoryPlan = MemoryPlan<String>()
        val doPlan = memoryPlan.doPlan(5, ('a'..'z').map { it.toString() }.toList())
        doPlan.forEach({ day ->
            println("日期：" + day.date)
            println(
                "内容：" + day.studyGroup.stream().map { it.tag + " -> " + it.studySomething }
                    .collect(Collectors.joining("; ")))
        })
    }

    @Test
    fun doPlan02() {
        val memoryPlan = MemoryPlan<String>()
        val doPlan = memoryPlan.doPlan(5, ('a'..'z').map { it.toString() }.toList())

        // 任务应该有五个队列
        // 熟练度由低到高，队列1 ~ 队列5
        // 队列1 中的单词全部都是新词
        // 如果记住,单词出队，并且进入队列X+1; 如果没有记住，单词还是在队列X
        // 单词中含有下次出现时间，如果 下次出现时间 < 当前时间, 也应加入本次学习计划中
        val container = memoryPlan.initPlanContainer(('a'..'z').map { it.toString() }.toList())

       doPlan.map { it.date }.stream().forEach { date ->
           container.remark(date, )
       }
       /* doPlan.map { it.date }.stream().forEach { date ->
            // 学习的任务由 复习任务 + 新任务 组成
            // 复习任务 = 历史待复习任务
            val dateStudyTask = memoryPlan.dateStudyTask(date, doPlan)
            println("日期：$date, 任务：$dateStudyTask")
            val mutableListOf = mutableListOf<String>()
            dateStudyTask.stream().forEach { word ->
                if (Math.random() < 0.70) {
                    print("  记住 > $word |")
                    mutableListOf.add(word)
                } else {
                    // 模拟记不住
                    print("  记不住 > $word |")
                }
            }
            println()
            println()
            memoryPlan.pushDateStudyTask(mutableListOf)
        }*/


    }
}