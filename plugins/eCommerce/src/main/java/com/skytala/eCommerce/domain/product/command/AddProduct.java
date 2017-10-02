package com.skytala.eCommerce.domain.product.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.product.event.ProductAdded;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProduct extends Command{

	private Product productToBeAdded;
	
	public AddProduct(Product productToBeAdded) {
		this.productToBeAdded = productToBeAdded;
	}
	
	@Override
	public Event execute() {
		
		Delegator delegator = DelegatorFactory.getDelegator("default");

		Product addedProduct = null;
		boolean success = false;
		try {
			productToBeAdded.setProductId(delegator.getNextSeqId("Product"));
			productToBeAdded.setAutoCreateKeywords(false);
			GenericValue newValue = delegator.makeValue("Product", productToBeAdded.mapAttributeField());
			addedProduct = ProductMapper.map(delegator.create(newValue));
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			addedProduct = null;
		}
		
		Event resultingEvent = new ProductAdded(addedProduct, success);
		Broker.instance().publish(resultingEvent);
		
		return resultingEvent;
		
	}

}
