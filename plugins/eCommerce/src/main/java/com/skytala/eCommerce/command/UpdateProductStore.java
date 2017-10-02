package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductStore;
import com.skytala.eCommerce.event.ProductStoreUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class UpdateProductStore implements Command {

	private ProductStore elementToBeUpdated;

	public UpdateProductStore(ProductStore elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	public ProductStore getElementToBeUpdated() {
		return elementToBeUpdated;
	}

	public void setElementToBeUpdated(ProductStore elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	@Override
	public void execute() throws RecordNotFoundException {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			GenericValue newValue = delegator.makeValue("ProductStore", elementToBeUpdated.mapAttributeField());
			delegator.store(newValue);
			if (delegator.store(newValue) == 0) {
				throw new RecordNotFoundException(ProductStore.class);
			}
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}
		Broker.instance().publish(new ProductStoreUpdated(success));
	}
}
