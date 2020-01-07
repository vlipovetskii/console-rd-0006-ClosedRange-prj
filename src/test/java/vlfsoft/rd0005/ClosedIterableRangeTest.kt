package vlfsoft.rd0005

import org.amshove.kluent.`should equal`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestFactory
import vlfsoft.rd0003.kTestFactory
import java.time.LocalDate

class KClosedIterableRangeTest {

    @TestFactory
    fun testFactory() = kTestFactory {

        "startDate..startDate + 2.days -> iterable" {

            val startDate = currentLocalDate
            val expectedItems = listOf(startDate, startDate + 1.days, startDate + 2.days)
            val datesRange: ClosedRange<LocalDate> = startDate..startDate + 2.days
            /**
             * PRB: [ClosedRange] does not support neither forEach nor asIterable
             * WO: [KClosedIterableRangeA] + set of utility functions.
             */
            val datesRangeIterable = datesRange.toLocalDateClosedRangeIterable

            datesRangeIterable.first() `should equal` datesRange.start
            datesRangeIterable.last() `should equal` datesRange.endInclusive
            datesRangeIterable.count() `should equal` ((datesRange.endInclusive - datesRange.start) + 1)

            Assertions.assertIterableEquals(expectedItems, datesRangeIterable)

            datesRange.toLocalDateClosedRangeIterable.forEach {
                println("$it")
            }

        }
    }

}