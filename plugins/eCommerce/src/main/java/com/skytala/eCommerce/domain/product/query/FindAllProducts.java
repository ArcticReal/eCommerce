package com.skytala.eCommerce.domain.product.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.product.event.ProductFound;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;;

public class FindAllProducts extends Query{

	@Override
	public Event execute() {
		
		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<Product> foundProducts = new ArrayList<Product>();		

		try {
			List<GenericValue> buf = delegator.findAll("Product", false);
			
			for (int i = 0; i < buf.size(); i++) {
				
				foundProducts.add(ProductMapper.map(buf.get(i)));
			}
			
			
		}catch(GenericEntityException e) {
			e.printStackTrace();
		}
		
		Broker.instance().publish(new ProductFound(foundProducts));
		return null;
		
	}

}
