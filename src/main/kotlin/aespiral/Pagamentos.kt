/*
 * Domain modeling made functional, Scott Wlaschin, Cap. 4
 */

package aespiral

data class NumCheque(val num : Int)
data class NumCartao(val num: String)

enum class Bandeira {
    Visa, Mastercard, Inexistente
}

data class InfoCartao(
    val bandeira: Bandeira,
    val numero: NumCartao
)

sealed class MetodoPagamento
object Dinheiro : MetodoPagamento()
data class Cheque(val no:NumCheque): MetodoPagamento()
data class Cartao(val dados:InfoCartao): MetodoPagamento()

data class ValorPago(val valor: Double)
enum class Moeda { EUR, USD, BRL }

data class Pagamento (
    val valor: ValorPago,
    val moeda: Moeda,
    val metodo: MetodoPagamento
)

val bd = listOf(
    Pagamento(
        ValorPago(126.80),
        Moeda.BRL,
        Dinheiro
    ),
    Pagamento(
        ValorPago(249.55),
        Moeda.BRL,
        Cheque(NumCheque(123456))
    ),
    Pagamento(
        ValorPago(578.90),
        Moeda.USD,
        Cartao( InfoCartao( Bandeira.Mastercard, NumCartao("1234000067895555")))
    ),
    Pagamento(
        ValorPago( 98.0),
        Moeda.EUR,
        Cartao( InfoCartao( Bandeira.Visa, NumCartao( "3692777787651111")))
    )
)

fun bandeira(pgm: Pagamento) : Option<Bandeira> =
    when (pgm.metodo) {
        is Cartao -> Some(pgm.metodo.dados.bandeira)
        else -> None
    }

val bandeiras =
    bd.map(::bandeira)
        .filter { ! it.isEmpty() }
        .toSet()
        .map { when (it) {is Some -> it.value; is None -> Bandeira.Inexistente} }

fun main() = print(bandeiras)