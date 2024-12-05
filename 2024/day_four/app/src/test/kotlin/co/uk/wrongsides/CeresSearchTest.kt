package co.uk.wrongsides

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class CeresSearchTest {

    @Test
    fun create_a_word_search() {
        val wordsearch = CeresSearch()
        val input = """
                AB
                CD
                """.trimIndent()

        val grid = wordsearch.create(input)

        val expected = arrayOf(charArrayOf('A', 'B'), charArrayOf('C', 'D'))
        assert(expected.contentDeepEquals(grid))
    }

    @Test
    fun find_xmas_spelt_to_east_is_true() {
        val wordsearch = CeresSearch()
        val input = """
                XMAS
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.horizontalForwards(0, 0))
    }

    @Test
    fun find_xmas_misspelt_to_east_is_false() {
        val wordsearch = CeresSearch()
        val input = """
                XSAM
                """.trimIndent()
        wordsearch.create(input)

        assertFalse(wordsearch.horizontalForwards(0, 0))
    }

    @Test
    fun find_xmas_spelt_to_west_is_true() {
        val wordsearch = CeresSearch()
        val input = """
                SAMX
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.horizontalBackwards(3, 0))
    }

    @Test
    fun find_xmas_misspelt_to_west_is_false() {
        val wordsearch = CeresSearch()
        val input = """
                XSAM
                """.trimIndent()
        wordsearch.create(input)

        assertFalse(wordsearch.horizontalBackwards(3, 0))
    }

    @Test
    fun find_xmas_spelt_to_south_is_true() {
        val wordsearch = CeresSearch()
        val input =
                """
                X
                M
                A
                S
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.verticalForwards(0, 0))
    }

    @Test
    fun find_xmas_misspelt_to_south_is_false() {
        val wordsearch = CeresSearch()
        val input =
                """
                X
                S
                A
                M
                """.trimIndent()
        wordsearch.create(input)

        assertFalse(wordsearch.verticalForwards(0, 0))
    }

    @Test
    fun find_xmas_spelt_to_north_is_true() {
        val wordsearch = CeresSearch()
        val input =
                """
                S
                A
                M
                X
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.verticalBackwards(0, 3))
    }

    @Test
    fun find_xmas_misspelt_to_north_is_false() {
        val wordsearch = CeresSearch()
        val input =
                """
                X
                S
                A
                M
                """.trimIndent()
        wordsearch.create(input)

        assertFalse(wordsearch.verticalBackwards(0, 3))
    }

    @Test
    fun find_xmas_spelt_south_east_is_true() {
        val wordsearch = CeresSearch()
        val input =
                """
                XAAA
                BMBB
                CCAC
                DDDS
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.southEast(0, 0))
    }

    @Test
    fun find_xmas_spelt_south_west_is_true() {
        val wordsearch = CeresSearch()
        val input =
                """
                AAAX
                BBMB
                CACC
                SDDD
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.southWest(3, 0))
    }

    @Test
    fun find_xmas_spelt_north_west_is_true() {
        val wordsearch = CeresSearch()
        val input =
                """
                SAAA
                BABB
                CCMC
                DDDX
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.northWest(3, 3))
    }

    @Test
    fun find_xmas_spelt_north_east_is_true() {
        val wordsearch = CeresSearch()
        val input =
                """
                AAAS
                BBAB
                CMCC
                XDDD
                """.trimIndent()
        wordsearch.create(input)

        assert(wordsearch.northEast(0, 3))
    }

    @Test
    fun find_xmas_18_times_in_example_word_search() {
        val wordsearch = CeresSearch()
        val example =
                """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """.trimIndent()
        wordsearch.create(example)

        val count = wordsearch.findXmas()

        assertEquals(18, count)
    }

    @Test
    fun find_all_xmas_in_file() {
        val wordsearch = CeresSearch()
        wordsearch.createFromFile("input")

        val count = wordsearch.findXmas()

        assertEquals(2547, count)
    }
}
