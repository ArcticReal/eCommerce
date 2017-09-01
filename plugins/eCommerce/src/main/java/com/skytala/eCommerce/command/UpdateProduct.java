package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.event.ProductUpdated;

public class UpdateProduct implements Command{

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
	public void execute() {
		
		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			GenericValue newValue = delegator.makeValue("Product", productToBeUpdated.mapAttributeField());
			delegator.store(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}
		
		Broker.instance().publish(new ProductUpdated(success));

		
	}

}
