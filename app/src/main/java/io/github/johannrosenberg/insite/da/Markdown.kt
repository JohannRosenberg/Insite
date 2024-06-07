package io.github.johannrosenberg.insite.da

class Markdown() {
    val INDENT_SIZE = 3 // Number of spaces that make up an indent. Tabs should be replaced with spaces of this size.

    fun convertMarkdownToMetaData(markdown: String): MutableList<LineInfo> {
        val lines = mutableListOf<LineInfo>()
        val items = markdown.split("\\n")


        items.forEach {
            when (true) {
                it.trim().startsWith("#") -> {
                    val hashEndPos = it.indexOf(" ")
                    lines.add(LineInfo(text = it.substring(hashEndPos + 1).trim(), lineType = headerLevel(it)))
                }

                it.trim().startsWith("* ") -> {
                    lines.add(LineInfo(text = it.substring(2).trim(), lineType = LineType.BulletedListItem, indentSize = indentSize(it)))
                }

                isNumberedList(it.trim()) -> {
                    lines.add(LineInfo(text = it.trim(), lineType = LineType.NumberedListItem, indentSize = indentSize(it)))
                }

                it.trim().isEmpty() -> {
                    lines.add(LineInfo(text = it.trim(), lineType = LineType.BlankLine))
                }

                else -> lines.add(LineInfo(text = it.trim(), lineType = LineType.Paragraph, indentSize = indentSize(it)))
            }
        }

        return lines
    }

    private fun isNumberedList(text: String): Boolean {
        val line = text.trim()
        val spacePos = line.indexOf(" ")

        if (spacePos < 0)
            return false

        if (line.substring(spacePos - 1, spacePos) != ".")
            return false

        val number = line.substring(0, spacePos - 1)

        return try {
            number.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun headerLevel(text: String): LineType {
        val line = text.trim()
        var count = 0

        for (i in 0..text.lastIndex) {
            if (text.substring(i, i + 1) == "#") {
                count++
            } else {
                break
            }
        }

        return when (count) {
            1 -> LineType.H1
            2 -> LineType.H2
            3 -> LineType.H3
            4 -> LineType.H4
            5 -> LineType.H5
            else -> LineType.H6
        }
    }

    private fun indentSize(text: String): Int {
        var count = 0

        for (i in 0..text.lastIndex) {
            if (text.substring(i, i + 1) == " ") {
                count++
            } else {
                break
            }
        }

        return count / INDENT_SIZE
    }
}

data class LineInfo(val text: String, val lineType: LineType, val indentSize: Int = 0)

enum class LineType {
    H1,
    H2,
    H3,
    H4,
    H5,
    H6,
    BulletedListItem,
    NumberedListItem,
    Paragraph,
    BlankLine
}