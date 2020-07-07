/*
 * Domain modeling made functional, Scott Wlaschin, Cap. 4
 */

package aespiral

data class Pessoa(val prenome:String, val sobrenome:String)

val alguem = Pessoa("Silvia", "Lanes")

sealed class Quanto
data class Unidade(val quant : Int) : Quanto()
data class Peso(val quilos : Double) : Quanto()

val pedidoEmUnidades = Unidade(10)
val pedidoEmKg = Peso(2.5)

fun mostraQuanto (pedido: Quanto) =
    when (pedido) {
        is Unidade -> println( "${pedido.quant} unidades" )
        is Peso -> println( "${pedido.quilos} quilos")
    }

fun main() {
    mostraQuanto(pedidoEmUnidades)
    mostraQuanto(pedidoEmKg)
}
