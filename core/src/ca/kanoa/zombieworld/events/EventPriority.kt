package ca.kanoa.zombieworld.events

enum class EventPriority private constructor(private val level: Int) {

    LOG(10),
    HIGH(8),
    DEFAULT(5),
    LOW(3)

}
