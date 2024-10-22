package cc.miaooo.service

import cc.miaooo.service.MemoryPlan.PlanContainerImpl
import org.junit.jupiter.api.Test
import java.time.LocalDate
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
        val doPlan1 = doPlan.stream()
            .flatMap { it ->
                val list = it.studyGroup.stream().flatMap { it.studySomething.stream() }
                    .map { w -> Word(it.date, w) }.toList()
                return@flatMap list.stream()
            }.toList()
        val container = PlanContainerImpl(doPlan1)

        (0..100).map { LocalDate.now().plusDays(it.toLong()) }
            .map { it }
            .stream().forEach { date ->
                println("日期：$date")
                container.remark(
                    { (it.date == date || it.date.isBefore(date)) && it.count <= 4 },
                    {
                        if (Math.random() < 0.3) {
                            print("  记住 > ${it.word} |")
                            if (it.count < 4) {
                                val intArrayOf = intArrayOf(1, 2, 4, 8)
                                it.date = date.plusDays(intArrayOf[it.count].toLong());
                                print("  下次复习时间 > ${it.date} |")
                            } else {
                                print("  #完成单词# > ${it.date} |")
                            }
                            it.count += 1;
                        } else {
                            // 模拟记不住
                            print("  记不住 > ${it.word} |")
                        }
                    })
                println()
                println()
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

data class Word(var date: LocalDate, val word: String, var count: Int = 0) {}
