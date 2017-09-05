package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.util.EntityListIterator;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.entity.ProductPriceMapper;
import com.skytala.eCommerce.event.ProductPricesFound;

public class FindProductPricesById implements Query {

	private String productId;

	public FindProductPricesById(String productId) {
		this.setProductId(productId);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		List<ProductPrice> foundProductPrices = new LinkedList<>();
		
		try {
			
			EntityCondition cond = EntityCondition.makeCondition("productId", productId);
			EntityListIterator iterator = delegator.find("ProductPrice", cond, null, null, null, null);
			
			GenericValue value = new GenericValue();
			while((value = iterator.next()) != null) {
				foundProductPrices.add(ProductPriceMapper.map(value));
			}
			
			
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		Broker.instance().publish(new ProductPricesFound(foundProductPrices));
	}

}
