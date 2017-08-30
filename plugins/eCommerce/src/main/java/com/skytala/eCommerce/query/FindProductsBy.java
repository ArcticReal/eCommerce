package com.skytala.eCommerce.query;

import java.util.ArrayList;
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

	Map<String, Object> filter;
	
	public FindProductsBy(Map<String, Object> newFilter) {
		this.filter = newFilter;
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
	
		String[] keySet = (String[]) filter.keySet().toArray();
		boolean returnVal = true;
		
		for(int i = 0; i < filter.size(); i++) {


			if(!filter.get(keySet[i]).equals(val.get(keySet[i]))) {
				returnVal = false;
				break;
			}
		}
		
		return returnVal;
	}

}
