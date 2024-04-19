class Shop {
    val listProducts = arrayListOf<Product>()

    init {
        listProducts.add(Product("Banana", 50, 100, """
             _
            //\\
            V  \\
             \  \_
              \,'.`-.
              |     `
              \      `,
               `.     `,
                 `-.__.'
        """))
        listProducts.add(Product("Apple", 30, 150,"""
       ,--./,-.
     / #      \\
    |          |
     \        / 
      `._,._,'
    """))
        listProducts.add(Product("Soda", 20, 200, """
       ______
     /        \\
    |          |
    |          |
    |          |
     \\________/
    """))
    }

    //C- create создание
    // R
    // U
    // D


    //Read
    fun showProducts() {
        for ((index, product) in listProducts.withIndex()) {
            println("$index | ${product.title} - ${product.price}тг - ${product.quantity}шт ${product.ASCII}")
        }

    }

    //Create
    fun addProduct() {
        do {
            println("Какой товар хотите добавить?")
            print("Название: ")
            val titleNewProduct = readLine().toString()
            print("Цена(тг): ")
            val priceNewProduct = readLine().toString().toInt()
            print("Количество(шт): ")
            val quantityNewProduct = readLine().toString().toInt()
            print("ASCII изображение (нажмите Enter после завершения):")
            val ASCII = generateAsciiArtInput() //ASCII art

            val newProduct = Product(titleNewProduct, priceNewProduct, quantityNewProduct, ASCII)

            listProducts.add(newProduct)
            println("Товар успешно добавлен в полку, хотите еще добавлять: да/нет ")
            val choiceForLoop = readLine().toString().lowercase()
        } while (choiceForLoop == "да")
    }

    fun deleteProduct(): Product {
        var indexProduct=0
        var quantityToDelete = 0
        var checkIndex =true
        do {
            print("Индекс товара: ")
            indexProduct = readLine()?.toIntOrNull() ?: 0
            if (indexProduct < 0 || indexProduct >= listProducts.size) {
                println("Неверный номер продукта.Попробуйте еще раз")
                checkIndex = false
            }else{
                checkIndex= true
            }
        } while (!checkIndex)

        var product = listProducts[indexProduct]


        var checkQuantity =true
        do {
            print("Укажите количество товара (не более ${product.quantity}): ")
            quantityToDelete = readLine()?.toIntOrNull() ?: 0
            if (quantityToDelete > product.quantity) {
                println("Выбранное больше чем товар, что имеется.")
                checkQuantity= false
            }else{
                checkQuantity =true
            }
        } while (!checkQuantity)

        product.quantity -= quantityToDelete

        if (product.quantity == 0) {
            // Если количество товара стало нулевым, удаляем товар из списка
            listProducts.removeAt(indexProduct)
        }

        return Product(product.title,product.price,quantityToDelete, product.ASCII)
    }

    fun updateProduct() {
        var indexProduct = 0
        var checkIndex = true

        do {
            print("Индекс товара для изменения: ")
            indexProduct = readLine()?.toIntOrNull() ?: 0
            if (indexProduct < 0 || indexProduct >= listProducts.size) {
                println("Неверный номер продукта. Попробуйте еще раз.")
                checkIndex = false
            } else {
                checkIndex = true
            }
        } while (!checkIndex)

        val product = listProducts[indexProduct]

        var fieldToUpdate: Int
        do {
            println("Что вы хотите изменить?")
            println("1. Название")
            println("2. Цену")
            println("3. Количество")
            print("Выберите номер: ")
            fieldToUpdate = readLine()?.toIntOrNull() ?: 0
        } while (fieldToUpdate !in 1..3)

        when (fieldToUpdate) {
            1 -> {
                print("Введите новое название товара: ")
                val newTitle = readLine()?.toString() ?: ""
                product.title = newTitle
            }
            2 -> {
                print("Введите новую цену товара: ")
                val newPrice = readLine()?.toIntOrNull() ?: 0
                product.price = newPrice
            }
            3 -> {
                print("Введите новое количество товара (не более ${product.quantity}): ")
                val newQuantity = readLine()?.toIntOrNull() ?: 0
                if (newQuantity < 0) {
                    println("Количество товара не может быть отрицательным.")
                    return
                }
                product.quantity = newQuantity
            }
        }

        println("Товар успешно обновлен.")
    }

    //Для ASCII я честно не имею понятия как сделать код для ASCII
    private fun generateAsciiArtInput(): String {
        val asciiArtLines = mutableListOf<String>()
        var line: String?
        do {
            line = readLine()
            if (!line.isNullOrBlank()) {
                asciiArtLines.add(line)
            }
        } while (!line.isNullOrBlank())
        return asciiArtLines.joinToString("\n")
    }
}
