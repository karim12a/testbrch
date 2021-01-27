package net.codejava.ws;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	
	public static ProductDAO instance;
	public static List<Product> data = new ArrayList<>();
	static {
		
		data.add(new Product(1,"iphoone X",999.99f));
		data.add(new Product(2, "XBOX 1", 777.77f));
		
	}
	public ProductDAO() {
		
	}
	
	public static  ProductDAO getinstance() {
		
		if(instance== null)
			instance = new ProductDAO();
		
		return instance;
	}
	
	public List<Product> listAll() {
		return  new ArrayList<Product>(data) ;
		
	}
	
	public int  addProduct(Product prd) {
		int newId = data.size()+1;
		prd.setId(newId);
		data.add(prd);
		return newId;
		
	}

	public Product getProduct(int id) {
		
		Product productIndex = new Product(id);
		int index = data.indexOf(productIndex);
		if (index>0) {
			return data.get(index);
			
		}
		
		return null;
		
		}
	
	public boolean updateProduct(Product prodt) {
		
		int index = prodt.getId();
		
		if(index== data.indexOf(prodt)) {
			 data.set(index, prodt);
				
				return true;
			
		}
		
		return false;
		
		
	}
	
	public boolean delete(int id) {
		Product prd = new Product(id);
		int index = data.indexOf(prd);
		if(index>=0) {
			data.remove(index);
			return true;
		}
		return false;
	}

}
