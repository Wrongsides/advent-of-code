package co.uk.wrongsides

import java.io.InputStreamReader

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

    fun findXmas(): Int {
        var count = 0

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (horizontalForwards(x, y)) count++
                if (horizontalBackwards(x, y)) count++
                if (verticalForwards(x, y)) count++
                if (verticalBackwards(x, y)) count++
                if (northEast(x, y)) count++
                if (northWest(x, y)) count++
                // if (southEast(x, y)) count++
                // if (southWest(x, y)) count++
            }
        }
        return count
    }

    fun horizontalForwards(x: Int, y: Int): Boolean {
        if (x + 3 >= grid[y].size) return false
        val word = grid[y].sliceArray(x until x + 4).joinToString("")
        return "XMAS".equals(word)
    }

    fun horizontalBackwards(x: Int, y: Int): Boolean {
        if (x - 3 < 0) return false
        val word = grid[y].sliceArray(x - 3..x).joinToString("")
        return "SAMX".equals(word)
    }

    fun verticalForwards(x: Int, y: Int): Boolean {
        if (y + 3 >= grid.size) return false
        val word = (0..3).joinToString("") { grid[y + it][x].toString() }
        return "XMAS".equals(word)
    }

    fun verticalBackwards(x: Int, y: Int): Boolean {
        if (y - 3 < 0) return false
        val word = (0..3).joinToString("") { grid[y - it][x].toString() }
        return "XMAS".equals(word)
    }

    fun northWest(x: Int, y: Int): Boolean {
        if (x - 3 < 0 || y - 3 < 0) return false
        val word = (0..3).joinToString("") { grid[y - it][x - it].toString() }
        return "XMAS".equals(word) || "SAMX".equals(word)
    }

    fun northEast(x: Int, y: Int): Boolean {
        if (x + 3 >= grid[0].size || y - 3 < 0) return false
        val word = (0..3).joinToString("") { grid[y - it][x + it].toString() }
        return "XMAS".equals(word) || "SAMX".equals(word)
    }

    fun southEast(x: Int, y: Int): Boolean {
        if (x + 3 >= grid[0].size || y + 3 >= grid.size) return false
        val word = (0..3).joinToString("") { grid[y + it][x + it].toString() }
        return "XMAS".equals(word) || "SAMX".equals(word)
    }

    fun southWest(x: Int, y: Int): Boolean {
        if (x - 3 < 0 || y + 3 >= grid.size) return false
        val word = (0..3).joinToString("") { grid[y + it][x - it].toString() }
        return "XMAS".equals(word) || "SAMX".equals(word)
    }
}
