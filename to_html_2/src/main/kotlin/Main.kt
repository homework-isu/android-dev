import java.io.File
import Message


fun main(args: Array<String>) {
    val message = Message("danil2003iva@mail.ru", null, "kontent", "me")
    val out = File("outfile.html")
    out.writeText(message.toHTML())
}