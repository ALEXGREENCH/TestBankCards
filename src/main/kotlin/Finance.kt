import Utils.asResource
import Utils.luhn
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import rubin.RuBinList
import rubin.RuBinListDeserializer
import kotlin.math.pow
import kotlin.random.Random

class Finance {

    fun generateCard(bank: BANK = BANK.SBERBANK): String = CreditCard.generateNumber(CreditCard.getRandomBin(bank), 16)

    object CreditCard {

        fun getRandomBin(bank: BANK): String {
            "/json/rubin.json".asResource {
                println(it)

                val builder = GsonBuilder()
                builder.registerTypeAdapter(RuBinList::class.java, RuBinListDeserializer())
                val gson = builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                val ruBinList: RuBinList = gson.create().fromJson(it, RuBinList::class.java)

                val banksArrayList = ArrayList<Bank>()
                for (e in ruBinList.list){
                    // TODO: переделать на сортировку по enum-у
                    val mii = when(e.bin.first().digitToInt()){
                        2 -> PaySystemType.MIR
                        4 -> PaySystemType.VISA
                        5 -> PaySystemType.MASTERCARD
                        6 -> PaySystemType.MAESTRO
                        else -> throw IllegalAccessError()
                    }



                    println("bin: ${e.bin} | ${mii.bankName} | ${e.bank}")
                }
            }

            return  "220001"
        }



        fun generateNumber(prefix: String, length: Int): String {
            return completedNumber(prefix, length)
        }

        private fun completedNumber(prefix: String, length: Int): String {
            val userNumberLength = length - prefix.length - 2
            val startPoint = (10.0.pow(userNumberLength) - 1).toLong()
            val endPoint = (10.0.pow(userNumberLength + 1) - 1).toLong()
            val randomUserNumber = Random.nextLong(startPoint, endPoint).toString()
            val number = prefix + randomUserNumber
            println("GEN CARD prefix: $prefix")
            println("GEN CARD randomUserNumber: $randomUserNumber")
            return number + luhn(number)
        }
    }

    enum class BANK {
        SBERBANK,
        TINKOFF,
        ALPHA
    }

    enum class PaySystemType(val digit: Byte, val bankName: String) {
        MIR(2, "МИР"),
        VISA(4, "VISA"),
        MASTERCARD(5, "MASTERCARD"),
        MAESTRO(6, "MAESTRO");
    }
}

data class Bank(
    val name: String,
    val typePaymentList: ArrayList<BankTypePayment>
)

data class BankTypePayment(
    val typePaymentList: ArrayList<TypePaymentBin>
)

data class TypePaymentBin(
    val binList: ArrayList<String>
)