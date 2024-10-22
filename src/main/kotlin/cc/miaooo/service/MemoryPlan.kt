package cc.miaooo.service

import java.time.LocalDate
import java.util.stream.IntStream
import kotlin.math.ceil

class MemoryPlan<T> {
    private val dateStudyTaskList: MutableList<T> = mutableListOf();

    /**
     * 获取复习的单词列表
     */
    fun reviewWords(historyWords: List<T>): List<T> {
        return emptyList()
    }

    fun remarkWord(): (T) -> Unit {
        val mutableListOf = mutableListOf<T>()
        return { it: T ->
            mutableListOf.add(it)
        }
    }

    fun dateStudyTask(date: LocalDate, from: List<DateMemoryPlan<T>>): List<T> {
        val toList =
            from.filter { it.date == date }
                .flatMap { it.studyGroup }
                .flatMap { it.studySomething }
                .toList()
        return toList
    }

    fun doPlan(batchSize: Int, studySomething: List<T>): List<DateMemoryPlan<T>> {
        // 新词天数
        val day = ceil(studySomething.size / batchSize.toDouble()).toInt()
        // 总复习天数，计划总天数
        val totalReviewDays = day + 15
        println("每天新词数量：${batchSize}, 学新词天数：${day}, 总复习天数：${totalReviewDays}")
        val calcStudyContents = calcStudyContents(day, studySomething, batchSize)
        val contentsIndexes = (0..calcStudyContents!!.size).toList()
        val localDateStudyContentMap = (contentsIndexes zip calcStudyContents).map {
            // index, List<List<T>>
            val first = it.first
            val second = it.second
            return@map Pair(LocalDate.now().plusDays(first.toLong()), second)
        }.toMap()

        // contents filling into template
        val template = IntStream.range(0, totalReviewDays).mapToObj({
            return@mapToObj DateMemoryPlan<T>(
                LocalDate.now().plusDays(it.toLong()),
                mutableListOf()
            )
        }).toList()

        val dateMemoryPlans = template.map({
            val studyContent = localDateStudyContentMap.getOrElse(it.date) { null }
            if (studyContent == null) {
                return@map it.copy(studyGroup = it.studyGroup)
            } else {
                val item = Item(it.date.toString(), studyContent)
                return@map it.copy(studyGroup = listOf(item))
            }
        })

        println(dateMemoryPlans)

        // 计算出 任务堆叠 的位置
        // [(A)] => [(A,[B]))]
        val map = dateMemoryPlans.flatMap { t ->
            // [1,2,4,7,15]
            val place = intArrayOf(1, 2, 4, 7, 15)
                .map { t.date.plusDays((it - 1).toLong()) }
                .toList()
            return@flatMap place.map { Pair(it, t.studyGroup) }
        }.groupBy({ it.first }, { it.second })
        return dateMemoryPlans.map { it ->
            val flatten =
                map.getOrElse(it.date) { listOf() }
                    .flatten()
                    .filter { it.studySomething.isNotEmpty() }
                    .toList()
            return@map it.copy(studyGroup = flatten)
        }
            .filter { it.studyGroup.isNotEmpty() }
            .toList()
    }

    private fun calcStudyContents(
        days: Int,
        studySomething: List<T>,
        batchSize: Int
    ): List<List<T>>? {
        return IntStream.range(0, days).mapToObj {
            val end = (it + 1) * batchSize
            val left = it * batchSize
            val right = if (end > studySomething.size) studySomething.size else end
            val batch = studySomething.subList(left, right)
            return@mapToObj batch;
        }.toList()
    }

    fun pushDateStudyTask(mutableListOf: MutableList<T>) {
        dateStudyTaskList.addAll(mutableListOf)
    }

    fun initPlanContainer(doPlan: List<T>): PlanContainer<T> {
        return PlanContainerImpl(doPlan)
    }

    interface PlanContainer<T> {
        fun remark(date: LocalDate, filter: (a: T) -> Boolean, io: (a: T) -> Unit)
    }

    class PlanContainerImpl<T>(doPlan: List<T>) : PlanContainer<T> {
        private val queue1: ArrayDeque<T> = ArrayDeque()

        init {
            doPlan.forEach(queue1::add)
        }

        override fun remark(date: LocalDate, filter: (a: T) -> Boolean, io: (a: T) -> Unit) {
            // 先复习，最后学习新词
            val set = mutableSetOf<T>()
            for (item in queue1) {
                if (filter(item)) {
                    io(item)
                }
            }
        }
    }

    data class Item<T>(val tag: String, val studySomething: List<T>) {}
    data class DateMemoryPlan<T>(
        val date: LocalDate,
        val studyGroup: List<Item<T>>
    ) {}
}