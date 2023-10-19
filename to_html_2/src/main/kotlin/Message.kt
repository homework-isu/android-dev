class Message(val address: String?, val topic: String?, val kontent: String?, val author: String?) {
    fun toHTML() : String {
        var template = ""
        address.let {
            template += ""
        }
        address?.let { template += "<tr><td>address</td><td>$it</td></tr> \n" }
        topic?.let { template += "<tr><td>topic</td><td>$it</td></tr> \n" }
        kontent?.let { template += "<tr><td>kontent</td><td>$it</td></tr> \n" }
        author?.let { template += "<tr><td>author</td><td>$it</td></tr> \n" }
        if (template.length == 0) {
            return ""
        }
        val head = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n<title>Styled Table</title>\n<style>table {font-family: Arial, sans-serif; border-collapse: collapse; width: 200px; margin: 20px;} tr:nth-child(even) {background-color: #f2f2f2;} td {border: 1px solid #dddddd; text-align: left; padding: 8px;}</style>\n</head>\n<body>"

        return  head + "<table>${template}</table>"
    }
}