class Admin(private val shop: Shop) {

    fun addProduct() {
        shop.addProduct()
    }

    fun removeProduct() {
        shop.showProducts()
        val removedProduct = shop.deleteProduct()
        println("Товар ${removedProduct.title} в количестве ${removedProduct.quantity} удален из магазина.")
    }

    fun updateProduct() {
        shop.showProducts()
        shop.updateProduct()
    }

    fun viewSoldProductsAndTotalProfit(soldProducts: List<Product>) {
        println("Проданные товары:")
        for (product in soldProducts) {
            println("${product.title} - ${product.price}тг - ${product.quantity}шт")
        }
        val totalProfit = soldProducts.sumOf { it.price * it.quantity }
        println("Общая прибыль: $totalProfit тг")
    }
}
