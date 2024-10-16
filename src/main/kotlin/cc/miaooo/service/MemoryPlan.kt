package cc.miaooo.service

import java.time.LocalDate
import java.util.stream.IntStream
import kotlin.math.ceil

class MemoryPlan<T> {
    fun doPlan(batchSize: Int, studySomething: List<T>): List<DateMemoryPlan<T>> {
        // 新词天数
        val day = ceil(studySomething.size / batchSize.toDouble()).toInt()
        // 总复习天数，计划总天数
        val totalReviewDays = day + 15
        println("每天新词数量：${batchSize}, 学新词天数：${day}, 总复习天数：${totalReviewDays}")
        val calcStudyContents = calcStudyContents(day, studySomething, batchSize)
        val contentsIndexes = (0..calcStudyContents!!.size).toList()
        val localDateStudyContentMap = (contentsIndexes zip calcStudyContents).map {
            return@map Pair(LocalDate.now().plusDays(it.first.toLong()), it.second)
        }.toMap()

        // contents filling into template
        val template = IntStream.range(0, totalReviewDays).mapToObj({
            return@mapToObj DateMemoryPlan<T>(
                LocalDate.now().plusDays(it.toLong()),
                mutableListOf()
            )
        }).toList()

        val dateMemoryPlans = template.map({
            val studyContent = localDateStudyContentMap.getOrElse(it.date) { it.studySomething }
            val item = Item(it.date.toString(), studyContent)
            return@map it.copy(studySomething = listOf(item))
        })

        println(dateMemoryPlans)

        // 计算出 任务堆叠 的位置
        // [(A)] => [(A,[B]))]
        val map = dateMemoryPlans.flatMap { t ->
            // [1,2,4,7,15]
            val place = intArrayOf(1, 2, 4, 7, 15)
                .map { t.date.plusDays((it - 1).toLong()) }
                .toList()
            return@flatMap place.map { Pair(it, t.studySomething) }
        }.groupBy({ it.first }, { it.second })
        return dateMemoryPlans.map { it ->
            val flatten =
                map.getOrElse(it.date) { listOf() }
                    .flatten()
                    .filter { it.studySomething.isNotEmpty() }
                    .toList()
            return@map it.copy(studySomething = flatten)
        }
            .filter { it.studySomething.isNotEmpty() }
            .toList()
    }

    private fun calcStudyContents(
        days: Int,
        studySomething: List<T>,
        batchSize: Int
    ): MutableList<List<T>>? {
        return IntStream.range(0, days).mapToObj {
            val end = (it + 1) * batchSize
            val left = it * batchSize
            val right = if (end > studySomething.size) studySomething.size else end
            val batch = studySomething.subList(left, right)
            return@mapToObj batch;
        }.toList()
    }

    private fun consumeList(
        studySomething: List<T>,
        toHere: MutableList<T>,
        count: Int
    ): List<T> {
        if (count == 0) {
            return toHere;
        }
        toHere += studySomething[0]
        return consumeList(studySomething.takeLast(studySomething.size - 1), toHere, count - 1)
    }

    data class Item<T>(val tag: String, val studySomething: List<T>) {}
    data class DateMemoryPlan<T>(
        val date: LocalDate,
        val studySomething: List<Item<Any?>>
    ) {}
}