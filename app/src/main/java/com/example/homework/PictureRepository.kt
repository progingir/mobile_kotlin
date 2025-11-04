data class Picture(
    val id: Int,
    val author: String,
    val url: String
)
private var nextId = 1

fun generateSamplePictures(): List<Picture> {
    return listOf(
        Picture(1, "Иван Петров", "https://avatar.iran.liara.run/public/90"),
        Picture(2, "Анна Смирнова", "https://avatar.iran.liara.run/public/94"),
        Picture(3, "Сергей Волков", "https://avatar.iran.liara.run/public/93"),
        Picture(4, "Мария Кузнецова", "https://avatar.iran.liara.run/public/91"),
        Picture(5, "Иван Иванов", "https://avatar.iran.liara.run/public/92")
    )
}

fun createNewPicture(): Picture {
    val newAuthor = listOf("Федор Михайлов", "Константин Рябов", "Людмила Некрасова").random()
    val newPicture = Picture(
        id = nextId,
        author = newAuthor,
        url = "https://avatar.iran.liara.run/public/${nextId}"
    )
    nextId++
    return newPicture
}