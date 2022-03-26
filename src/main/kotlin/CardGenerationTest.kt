object CardGenerationTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val cardNumber = Finance().generateCard()
        println("cardNumber $cardNumber")
    }
}