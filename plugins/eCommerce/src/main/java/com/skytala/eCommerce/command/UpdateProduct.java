package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.event.ProductUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

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
	public void execute() throws RecordNotFoundException{
		
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
		
		Broker.instance().publish(new ProductUpdated(success));

		
	}

}
