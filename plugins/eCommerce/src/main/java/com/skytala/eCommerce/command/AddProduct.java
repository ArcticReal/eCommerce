package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.control.Command;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.event.ProductAdded;

public class AddProduct implements Command{

	private Product productToBeAdded;
	
	public AddProduct(Product productToBeAdded) {
		this.productToBeAdded = productToBeAdded;
	}
	
	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			productToBeAdded.setProductId(delegator.getNextSeqId("Product"));
			productToBeAdded.setAutoCreateKeywords(false);
			GenericValue newValue = delegator.makeValue("Product", productToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}
		
		Broker.instance().publish(new ProductAdded(success));
		
	}

}
