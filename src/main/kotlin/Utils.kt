object Utils {
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun String.asResource(work: (String) -> Unit) {
        val content = this.javaClass::class.java.getResource(this).readText()
        work(content)
    }

    fun luhn(number: String, doubleOdd: Boolean = true): Int {
        val numberStr = number.reversed()
        var doublePositions = 1
        if (!doubleOdd) {
            doublePositions = 0
        }
        var sum = 0

        for (i in numberStr.indices) {
            var numberAtI = Character.getNumericValue(numberStr[i])
            if ((i + 1) % 2 == doublePositions) {
                numberAtI *= 2
            }
            if (numberAtI > 9) {
                numberAtI = numberAtI % 10 + 1
            }
            sum += numberAtI
        }

        var controlSum = sum % 10
        if (controlSum != 0) {
            controlSum = 10 - controlSum
        }

        return controlSum
    }
}