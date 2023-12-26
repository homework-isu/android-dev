

data class Message(val address: String?, val topic: String?, val kontent: String?, val author: String?) {
    fun toHTML() : String {
        var template = ""
        address.let {
            template += ""
        }
        address?.let { template += "<tr><td>address</td><td>$it</td></tr> \n" }
        topic?.let { template += "<tr><td>topic</td><td>$it</td></tr> \n" }
        kontent?.let { template += "<tr><td>kontent</td><td>$it</td></tr> \n" }
        author?.let { template += "<tr><td>author</td><td>$it</td></tr> \n" }
        if template.length == 0 {
            return ""
        }

        return  "<table>${template}</table>"
    }
}