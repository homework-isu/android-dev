import java.io.FileInputStream
import java.io.PrintWriter
import java.util.*

fun main() {
    val rolesList = mutableListOf<String>()
    val scriptLines = mutableListOf<Pair<Int, String>>()

    val scanner = Scanner(FileInputStream("input.txt"))

    readInputSections(scanner, rolesList, scriptLines)

    val roleMap = createRoleMap(scriptLines)

    printRoleLinesToFile(rolesList, roleMap, "output.txt")
}

fun readInputSections(scanner: Scanner, rolesList: MutableList<String>, scriptLines: MutableList<Pair<Int, String>>) {
    var isRolesSection = false
    var isTextLinesSection = false
    var lineNumber = 0

    while (scanner.hasNextLine()) {
        val line = scanner.nextLine().trim()

        when {
            line.startsWith("roles:") -> {
                isRolesSection = true
                isTextLinesSection = false
            }

            line.startsWith("textLines:") -> {
                isRolesSection = false
                isTextLinesSection = true
            }

            isRolesSection -> rolesList.add(line)
            isTextLinesSection -> {
                lineNumber++
                scriptLines.add(lineNumber to line)
            }
        }
    }
}

fun createRoleMap(scriptLines: List<Pair<Int, String>>): Map<String, MutableList<Pair<Int, String>>> {
    val roleMap = mutableMapOf<String, MutableList<Pair<Int, String>>>()

    for ((lineNumber, scriptLine) in scriptLines) {
        val roleDelimiterIndex = scriptLine.indexOf(":")
        val roleName = scriptLine.substring(0, roleDelimiterIndex).trim()
        val lineText = scriptLine.substring(roleDelimiterIndex + 1).trim()

        roleMap.computeIfAbsent(roleName) { mutableListOf() }.add(lineNumber to lineText)
    }

    return roleMap
}

fun printRoleLinesToFile(rolesList: List<String>, roleMap: Map<String, List<Pair<Int, String>>>, fileName: String) {
    PrintWriter(fileName).use { writer ->
        for (role in rolesList) {
            val roleName = role.trim()
            val roleLines = roleMap[roleName] ?: emptyList<Pair<Int, String>>()

            if (roleLines.isNotEmpty()) {
                writer.println("$roleName:")
                roleLines.forEach { (index, line) ->
                    writer.println("$index) $line")
                }
                writer.println()
            }
        }
    }
}
