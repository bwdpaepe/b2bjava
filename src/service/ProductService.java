package service;

import domein.Product;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class ProductService
{
  GenericDao<Product> productRepo;
  
  public ProductService() {
	  this.productRepo = new GenericDaoJpa<>(Product.class);
  }
  
  public ProductService(GenericDao<Product> productRepo) {
	  this.productRepo = productRepo;
  }
  
  public Product getProductById(long productId) {
	  Product product = productRepo.get(productId);
	  if(product == null) {
		  throw new IllegalArgumentException("ongeldige productId");
	  }
	  return product;
  }
}
