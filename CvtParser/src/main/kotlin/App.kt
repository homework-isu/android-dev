import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class App(
    val app: String,
    val category: String,
    val rating: Double,
    val reviews: Int,
    val size: String,
    val installs: Int,
    val type: String,
    val price: Boolean,
    val contentRating: String,
    val genres: String,
    val lastUpdated: String,
    val currentVer: Int,
    val androidVer: Int
) {
    public fun json(): Map<String, Any> {
        return mapOf(
            "app" to app,
            "category" to category,
            "rating" to rating,
            "reviews" to reviews,
            "size" to size,
            "installs" to installs,
            "type" to type,
            "price" to price,
            "content_rating" to contentRating,
            "genres" to genres,
            "last_updated" to lastUpdated,
            "current_ver" to currentVer,
            "android_ver" to androidVer
        )
    }

    public fun jsonString(format: Boolean = true): String {
        var jsonResult = ""
        val json = this.json()
        val count = json.size
        var n = 0
        jsonResult += "{"
        if (format) {
            jsonResult += "\n"
        }
        for ((key, value) in json) {
            n++
            if (format) {
                jsonResult += "\t"
            }
            jsonResult += "\"$key\":"
            jsonResult += if (value is String) { " \"$value\"" } else { " $value" }
            jsonResult += if (n != count) {","} else {""}
            if (format) {
                jsonResult +=  "\n"
            }

        }
        jsonResult += "}"
        if (format) {
            jsonResult +=  "\n"
        }
        return jsonResult
    }

    companion object {
        fun fromCSV(line: String): App {
            val vals = mutableListOf<String>()
            var currentVal = StringBuilder()
            var flag = false

            for (c in line) {
                when {
                    c == ',' && !flag -> {
                        vals.add(currentVal.toString())
                        currentVal = StringBuilder()
                    }

                    c == '"' -> flag = !flag
                    else -> currentVal.append(c)
                }
            }
            vals.add(currentVal.toString())

            return App (
                app = vals[0],
                category = vals[1],
                rating = vals[2].toDouble() ?: 0.0,
                reviews = vals[3].toInt() ?: 0,
                size = vals[4],
                installs = vals[5].parseInstalls(),
                type = vals[6],
                price = vals[7].toBoolean(),
                contentRating = vals[8],
                genres = vals[9],
                lastUpdated = vals[10],
                currentVer = vals[11].androidVersionToApi(),
                androidVer = vals[12].androidVersionToApi()
            )
        }
    }

}

fun String.parseInstalls(): Int {
    val numberPattern = Regex("\\d+")
    val numberString = numberPattern.findAll(this).joinToString("") { it.value }
    return numberString.toIntOrNull() ?: 0
}

fun String.androidVersionToApi(): Int {
    val versionRegex = """(\d+\.\d+)""".toRegex()
    val matchResult = versionRegex.find(this)

    return matchResult?.groupValues?.get(1)?.toDoubleOrNull()?.let {
        when {
            it >= 1.0 && it < 1.5 -> 3
            it >= 1.5 && it < 1.6 -> 4
            it >= 2.0 && it < 2.1 -> 7
            it >= 2.2 && it < 2.3 -> 8
            it >= 2.3 && it < 3.0 -> 10
            it >= 3.0 && it < 4.0 -> 11
            it >= 4.0 && it < 4.1 -> 14
            it >= 4.1 && it < 4.4 -> 16
            it >= 4.4 && it < 5.0 -> 19
            it >= 5.0 && it < 5.1 -> 21
            it >= 6.0 && it < 7.0 -> 23
            it >= 7.0 && it < 7.1 -> 24
            it >= 8.0 && it < 8.1 -> 26
            it >= 9.0 && it < 10.0 -> 28
            it >= 10.0 && it < 11.0 -> 29
            it >= 11.0 && it < 12.0 -> 31
            it >= 13.0 && it < 14.0 -> 33
            it >= 14 -> 34
            else -> 1
        }
    } ?: 1
}
