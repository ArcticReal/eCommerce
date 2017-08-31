package com.skytala.eCommerce.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.control.Query;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ProductMapper;
import com.skytala.eCommerce.event.ProductsFound;

public class FindProductsBy implements Query{

	Map<String, String> filter;
	
	public FindProductsBy(Map<String, String> filter) {
		this.filter = filter;
	}
	
	@Override
	public void execute() {
		
		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Product> foundProducts = new ArrayList<Product>();		

		try {
			List<GenericValue> buf = delegator.findAll("Product", false);
			
			for (int i = 0; i < buf.size(); i++) {
				if(applysToFilter(buf.get(i))) {
					foundProducts.add(ProductMapper.map(buf.get(i)));
				}
				
			}
			
			
		}catch(GenericEntityException e) {
			e.printStackTrace();
		}
		
		Broker.instance().publish(new ProductsFound(foundProducts));
		
		
	}
	
	public boolean applysToFilter(GenericValue val) {
	

		Iterator<String> iterator = filter.keySet().iterator();

		
		while(iterator.hasNext()) {

			String key = iterator.next();

			if(val.get(key).getClass().equals(String.class)) {
				if(!((String) val.get(key)).contains(filter.get(key))) {
					return false;
				}
			}else if(!filter.get(key).equals(val.get(key))){
				return false;
			}

		}
		
		return true;
	}

}
