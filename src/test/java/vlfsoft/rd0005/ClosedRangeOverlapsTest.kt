package vlfsoft.rd0005

import org.amshove.kluent.`should equal`
import org.junit.jupiter.api.TestFactory
import vlfsoft.rd0003.kTestFactory
import java.time.LocalDate

infix fun <T : Comparable<T>> ClosedRange<T>.overlaps(other: ClosedRange<T>): Boolean =
        (start in other || endInclusive in other) || (other.start in this || other.endInclusive in this)

class ClosedRangeOverlapsTest {

    private data class TestData(val r1: ClosedRange<LocalDate>, val r2: ClosedRange<LocalDate>, val expectedResult: Boolean)

    @TestFactory
    fun testFactory() = kTestFactory {

        listOf(
                TestData(
                        LocalDate.of(2019, 1, 1)..LocalDate.of(2019, 1, 31),
                        LocalDate.of(2019, 1, 1)..LocalDate.of(2019, 1, 31),
                        true
                ),

                TestData(
                        LocalDate.of(2019, 1, 1)..LocalDate.of(2019, 1, 31),
                        LocalDate.of(2020, 1, 1)..LocalDate.of(2020, 1, 31),
                        false
                ),

                TestData(
                        LocalDate.of(2019, 1, 1)..LocalDate.of(2019, 1, 31),
                        LocalDate.of(2018, 1, 1)..LocalDate.of(2020, 1, 31),
                        true
                ),

                TestData(
                        LocalDate.of(2019, 1, 1)..LocalDate.of(2019, 1, 31),
                        LocalDate.of(2019, 1, 1)..LocalDate.of(2019, 2, 1),
                        true
                ),

                TestData(
                        LocalDate.of(2019, 1, 1)..LocalDate.of(2019, 1, 31),
                        LocalDate.of(2018, 1, 1)..LocalDate.of(2019, 1, 31),
                        true
                )

        ).forEach { testData ->
            "$testData" {
                testData.run {
                    (r1 overlaps r2) `should equal` expectedResult
                    (r2 overlaps r1) `should equal` expectedResult
                }
            }
        }

    }

}