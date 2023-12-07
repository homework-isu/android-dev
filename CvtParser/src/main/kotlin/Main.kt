import App
import java.io.File

fun main(args: Array<String>) {
    val filePath = "src/main/resources/googleplaystore.csv"

    val apps = File(filePath).readLines().drop(1).map {
        line -> App.fromCSV(line)
    }

    var jsonResult = "["

    for (app in apps) {
        jsonResult += app.jsonString()
    }

    jsonResult += "]"

    val out = File("src/main/resources/out.json")
    out.writeText(jsonResult)
}