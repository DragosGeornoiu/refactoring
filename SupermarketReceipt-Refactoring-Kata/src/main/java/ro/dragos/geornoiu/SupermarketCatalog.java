package ro.dragos.geornoiu;

public interface SupermarketCatalog {

  void addProduct(Product product, double price);

  double getUnitPrice(Product product);
}
