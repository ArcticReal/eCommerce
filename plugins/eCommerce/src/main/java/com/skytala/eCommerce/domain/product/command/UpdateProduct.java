package com.skytala.eCommerce.domain.product.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.product.event.ProductUpdated;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProduct extends Command{

	private Product productToBeUpdated;
	
	public UpdateProduct(Product productToBeUpdated) {
		this.setProductToBeUpdated(productToBeUpdated);
	}
	
	public Product getProductToBeUpdated() {
		return productToBeUpdated;
	}
	
	public void setProductToBeUpdated(Product productToBeUpdated) {
		this.productToBeUpdated = productToBeUpdated;
	}

	@Override
	public Event execute() throws RecordNotFoundException{
		
		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			GenericValue newValue = delegator.makeValue("Product", productToBeUpdated.mapAttributeField());
			if(delegator.store(newValue) == 0) {
				throw new RecordNotFoundException(Product.class);
			}
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
				throw new RecordNotFoundException(Product.class);
			}
			success = false;
		}
		
		
		Event resultingEvent = new ProductUpdated(success);
		Broker.instance().publish(resultingEvent);
		return resultingEvent;

		
	}

}