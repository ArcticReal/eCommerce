package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ProductMapper;
import com.skytala.eCommerce.event.ProductAdded;

public class AddProduct implements Command{

	private Product productToBeAdded;
	
	public AddProduct(Product productToBeAdded) {
		this.productToBeAdded = productToBeAdded;
	}
	
	@Override
	public void execute() {

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
		
		Broker.instance().publish(new ProductAdded(addedProduct, success));
		
	}

}
