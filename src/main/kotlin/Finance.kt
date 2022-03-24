import kotlin.math.pow
import kotlin.random.Random

class Finance {

    //fun sberCard(): String = CreditCard.generateNumber(CreditCard.sberCard, 16, 1)[0]
    fun sberCard(): String = CreditCard.generateNumber(CreditCard.sberCard.random(), 16)

    object CreditCard {

        val sberCard = listOf(
            "427901",
            "636933",
            "639002",
            "427644",
            "427631",
            "427601",
            "220220",
        )

        /*      // TODO: BIN-ы visa & mastercard
                https://www.freebinchecker.com/sberbank-of-russia_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/tinkoff-credit-systems-bank-cjsc_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/vtb-bank-ojsc_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/cjsc-alfa-bank_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/gazprombank-ojsc_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/jscb-rosbank_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/ojsc-promsvyazbank_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/raiffeisen-bank-ltd_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/llc-home-credit-and-finance-bank_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/otkritie-bank-jsc_russian-federation-issuer-bin-list-country
                https://www.freebinchecker.com/russkiy-standard_russian-federation-issuer-bin-list-country?hl=ru
                https://www.freebinchecker.com/qiwi-bank-jsc_russian-federation-issuer-bin-list-country?hl=ru
         */


        private fun luhn(number: String, doubleOdd: Boolean = true): Int {
            val numberStr = number.reversed()
            var doublePositions = 1
            if(!doubleOdd) {
                doublePositions = 0
            }
            var sum = 0

            for(i in numberStr.indices) {
                var numberAtI = Character.getNumericValue(numberStr[i])
                if((i + 1) % 2 == doublePositions) {
                    numberAtI *= 2
                }
                if(numberAtI > 9) {
                    numberAtI = numberAtI % 10 + 1
                }
                sum += numberAtI
            }

            var controlSum = sum % 10
            if(controlSum != 0) {
                controlSum = 10 - controlSum
            }

            return controlSum
        }

        //fun generateNumber(prefixList: List<String>, length: Int, howMany: Int): MutableList<String>{
        //    val arr = mutableListOf<String>()
        //    for (i in 0 until howMany){
        //        arr.add(generateNumber(prefixList.random(), length))
        //    }
        //    return arr
        //}


        fun generateNumber(prefix: String, length: Int): String {
            return completedNumber(prefix, length)
        }

        private fun completedNumber(prefix: String, length: Int): String {
            val userNumberLength = length - prefix.length - 1
            val startPoint = (10.0.pow(userNumberLength) - 1).toLong()
            val endPoint = (10.0.pow(userNumberLength + 1) - 1).toLong()
            val randomUserNumber = Random.nextLong(startPoint, endPoint).toString()
            val number = prefix + randomUserNumber
            println("GEN CARD prefix: $prefix")
            println("GEN CARD randomUserNumber: $randomUserNumber")
            return number + luhn(number)
        }
    }
}