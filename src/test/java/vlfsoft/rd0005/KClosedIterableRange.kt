package vlfsoft.rd0005

import java.time.LocalDate

interface KClosedIterableRangeA<T : Comparable<T>> : ClosedRange<T>, Iterable<T>

internal class KClosedIterableRange<T : Comparable<T>>(override val start: T, override val endInclusive: T, private val nextBlock: (T) -> T) :
        KClosedIterableRangeA<T> {

    constructor(range: ClosedRange<T>, nextBlock: (T) -> T) : this(range.start, range.endInclusive, nextBlock)

    private class IteratorImpl<T : Comparable<T>>(private val range: ClosedRange<T>, private val nextBlock: T.() -> T) : Iterator<T> {
        private var current = range.start

        override fun hasNext() = current <= range.endInclusive

        override fun next() = current.also { current = nextBlock(current) }
    }

    override fun iterator(): Iterator<T> = IteratorImpl(this, nextBlock)

}

fun <T : Comparable<T>> ClosedRange<T>.asIterable(nextBlock: (T) -> T): KClosedIterableRangeA<T> = KClosedIterableRange(this, nextBlock)

@Suppress("unused")
fun <T : Comparable<T>> Pair<T, T>.asIterable(nextBlock: (T) -> T): KClosedIterableRangeA<T> = KClosedIterableRange(first, second, nextBlock)

@Suppress("unused")
fun <T : Comparable<T>> closedRangeIterableOf(start: T, endInclusive: T, nextBlock: (T) -> T): KClosedIterableRangeA<T> = KClosedIterableRange(start, endInclusive, nextBlock)

val ClosedRange<LocalDate>.toLocalDateClosedRangeIterable get() = asIterable { it + 1.days }
