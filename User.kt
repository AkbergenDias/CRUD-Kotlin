class User(private val shop: Shop) {

    private val cart = mutableListOf<Product>()

    fun addToCart() {
        shop.showProducts()
        print("Введите номер товара для добавления в корзину: ")
        val index = readLine()?.toIntOrNull() ?: return
        val product = shop.listProducts.getOrNull(index) ?: return
        println("Введите количество товара для добавления в корзину: ")
        val quantity = readLine()?.toIntOrNull() ?: return
        if (quantity <= 0 || quantity > product.quantity) {
            println("Некорректное количество товара.")
            return
        }
        cart.add(Product(product.title, product.price, quantity, product.ASCII))
        product.quantity = product.quantity - quantity
        println("Товар добавлен в корзину.")
    }

    fun removeFromCart() {
        showCart()
        print("Введите номер товара для удаления из корзины: ")
        val index = readLine()?.toIntOrNull() ?: return
        val product = cart.getOrNull(index) ?: return
        shop.listProducts.find { it.title == product.title }?.let {
            it.quantity += product.quantity
        }
        cart.removeAt(index)
        println("Товар удален из корзины и возвращен на полку.")
    }

    fun viewProductsInShop() {
        shop.showProducts()
    }

    fun viewCart() {
        showCart()
    }

    private var defaultUserMoney = 5000

    fun userMoney(): Int {

        return defaultUserMoney
    }


    fun buyAllProducts() {
        println("Вы приобрели следующие товары:")
        for (product in cart) {
            println("${product.title} - ${product.price}тг - ${product.quantity}шт")
        }
        println("Укажите адрес доставки выбрав номер района:")
        val homeAddress = arrayOf("Алмалинский", "Бостандыкский", "Медеуский", "Ауезовский")
        homeAddress.forEachIndexed { index, district ->
            println("${index + 1}. $district")
        }


        val addressChoice = readLine()?.toIntOrNull() ?: 0

        var deliveryCost = 0
        when (addressChoice) {
            1 -> deliveryCost = 1000  // Алмалинский
            2 -> deliveryCost = 1400  // Бостандыкский
            3 -> deliveryCost = 1200  // Медеуский
            4 -> deliveryCost = 1100  // Ауезовский
            else -> {
                println("Неверный выбор, попробуйте снова.")
                return
            }
        }

        var defaulUserMoney = defaultUserMoney
        val defaultCoupons = arrayOf(3000, 1500, 2000)
        val totalCost = cart.sumOf { it.price * it.quantity } + deliveryCost
        var endingCostWithoutCoupon = defaulUserMoney - totalCost

        println("Общая сумма покупки: $totalCost тг У вас доступно: $defaulUserMoney Хотите воспользоваться купоном? (Да/нет)")
        val couponChoice = readLine().toString().lowercase()
        if (couponChoice == "Да"){
            println("Выберите номер купона: 0, 1, 2")
        println(defaultCoupons.contentToString())


        val couponNumberChoice = readLine()?.toIntOrNull() ?: 0
        var costAfterCoupon = totalCost

        when (couponNumberChoice) {
            1 -> costAfterCoupon = totalCost - defaultCoupons[0]
            2 -> costAfterCoupon = totalCost - defaultCoupons[1]
            3 -> costAfterCoupon = totalCost - defaultCoupons[2]
            else -> {
                println("Неверный выбор, попробуйте снова.")
                return
            }
        }

        var endingCost = defaulUserMoney - costAfterCoupon

        if (costAfterCoupon > defaulUserMoney) println("У вас недостаточно средств")
        else
            println("Итого: $costAfterCoupon, Остаток:$endingCost")
        cart.clear()

    }
    else println("Итого: $totalCost, Остаток: $endingCostWithoutCoupon")
        cart.clear()
    }

    private fun showCart() {
        println("Товары в корзине:")
        for ((index, product) in cart.withIndex()) {
            println("$index | ${product.title} - ${product.price}тг - ${product.quantity}шт - ${product.ASCII}")
        }
    }
}
