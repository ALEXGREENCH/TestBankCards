object CardGenerationTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val genCard = Finance().sberCard()
        println("GEN CARD: $genCard")
    }
}