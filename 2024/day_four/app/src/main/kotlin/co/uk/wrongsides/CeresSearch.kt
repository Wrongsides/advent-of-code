package co.uk.wrongsides

import java.io.InputStreamReader
import kotlin.collections.indices

class CeresSearch {

    lateinit var grid: Array<CharArray>

    fun create(input: String): Array<CharArray> {
        grid = input.split("\n").map { it.toCharArray() }.toTypedArray()
        return grid
    }

    fun createFromFile(filename: String): Int {
        val inputStream =
                this::class.java.classLoader.getResourceAsStream(filename)
                        ?: throw IllegalArgumentException("File not found: $filename")
        grid =
                InputStreamReader(inputStream)
                        .buffered()
                        .readLines()
                        .map { it.toCharArray() }
                        .toTypedArray()

        return findXmas()
    }

    fun findCrossedMas(): Int {
        val word = "MAS"
        var count = 0
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (northEast(x, y, word) && x + 2 <= grid[y].size && northWest(x + 2, y, word))
                        count++
            }
        }
        return count
    }

    fun northEast(x: Int, y: Int, word: String = "XMAS"): Boolean {
        val limit = word.length - 1
        if (x + limit >= grid[0].size || y - limit < 0) return false
        val selection = (0..limit).joinToString("") { grid[y - it][x + it].toString() }
        return selection.equals(word) || selection.equals(word.reversed())
    }

    fun northWest(x: Int, y: Int, word: String = "XMAS"): Boolean {
        val limit = word.length - 1
        if (x - limit < 0 || y - limit < 0) return false
        val selection = (0..limit).joinToString("") { grid[y - it][x - it].toString() }
        return selection.equals(word) || selection.equals(word.reversed())
    }

    fun findXmas(): Int {
        var count = 0

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (north(x, y)) count++
                if (east(x, y)) count++
                if (south(x, y)) count++
                if (west(x, y)) count++
                if (northEast(x, y)) count++
                if (northWest(x, y)) count++
            }
        }
        return count
    }

    fun north(x: Int, y: Int): Boolean {
        if (y - 3 < 0) return false
        val word = (0..3).joinToString("") { grid[y - it][x].toString() }
        return "XMAS".equals(word)
    }

    fun east(x: Int, y: Int): Boolean {
        if (x + 3 >= grid[y].size) return false
        val word = grid[y].sliceArray(x until x + 4).joinToString("")
        return "XMAS".equals(word)
    }

    fun south(x: Int, y: Int): Boolean {
        if (y + 3 >= grid.size) return false
        val word = (0..3).joinToString("") { grid[y + it][x].toString() }
        return "XMAS".equals(word)
    }

    fun west(x: Int, y: Int): Boolean {
        if (x - 3 < 0) return false
        val word = grid[y].sliceArray(x - 3..x).joinToString("")
        return "SAMX".equals(word)
    }
}
