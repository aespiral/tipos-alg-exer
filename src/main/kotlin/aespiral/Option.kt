/*
 * "The Joy of Kotlin, Pierre-Yves Saumont, Listing 6.1
 */

package aespiral

sealed class Option<out A> {
    abstract fun isEmpty(): Boolean

    companion object {
        operator fun <A> invoke(a: A? = null): Option<A> = when (a) {
            null -> None
            else -> Some(a)
        }
    }
}

object None : Option<Nothing>() {
    override fun isEmpty() = true
    override fun toString() = "None"
    override fun equals(other: Any?): Boolean =
        other === None

    override fun hashCode(): Int = 0
}

data class Some<out A>(internal val value: A) : Option<A>() {
    override fun isEmpty() = false
}
