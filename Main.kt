fun main() {
    val shop = Shop()
    val admin = Admin(shop)
    val user = User(shop)

    var isRunning = true

    while (isRunning) {
        println("Выберите режим работы:")
        println("1. Режим администратора")
        println("2. Режим пользователя")
        println("3. Выйти из программы")
        print("Ваш выбор: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                var adminrunning = true //Добавил варайебл для лупа чтобы не сбрасывало в главное меню
                while (adminrunning) {
                    println("Вы в режиме администратора.")
                    println("Выберите действие:")
                    println("1. Добавить товар")
                    println("2. Удалить товар")
                    println("3. Обновить информацию о товаре")
                    println("4. Просмотреть проданные товары и общую прибыль")
                    println("5. Вернутся в главное меню")
                    print("Ваш выбор: ")
                    when (readLine()?.toIntOrNull()) {
                        1 -> admin.addProduct()
                        2 -> admin.removeProduct()
                        3 -> admin.updateProduct()
                        4 -> {
                            val soldProducts = listOf<Product>()
                            admin.viewSoldProductsAndTotalProfit(soldProducts)
                        }
                        5 -> adminrunning = false
                        else -> println("Некорректный выбор.")
                    }
                }
            }
            2 -> {
                var userRunning = true //Добавил варайебл для лупа чтобы не сбрасывало в главное меню
                while (userRunning) {
                    println("Вы в режиме пользователя. Баланс ${user.userMoney()} тенге") //в юзере есть функция дефолт мани
                    println("Выберите действие:")
                    println("1. Добавить товар в корзину")
                    println("2. Удалить товар из корзины")
                    println("3. Просмотреть товары в магазине")
                    println("4. Просмотреть корзину")
                    println("5. Купить все товары в корзине")
                    println("6. Вернутся в главное меню")
                    print("Ваш выбор: ")
                    when (readLine()?.toIntOrNull()) {
                        1 -> user.addToCart()
                        2 -> user.removeFromCart()
                        3 -> user.viewProductsInShop()
                        4 -> user.viewCart()
                        5 -> user.buyAllProducts()
                        6 -> userRunning = false
                        else -> println("Некорректный выбор.")
                    }
                }
            }
            3 -> {
                println("Выход из программы.")
                isRunning = false
            }
            else -> println("Некорректный выбор.")
        }
    }
}
