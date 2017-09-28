package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.event.ProductPriceAdded;

public class AddProductPrice implements Command {

	private ProductPrice elementToBeAdded;

	public AddProductPrice(ProductPrice elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			//elementToBeAdded.setProductPriceId(delegator.getNextSeqId("ProductPrice"));
			GenericValue newValue = delegator.makeValue("ProductPrice", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}

		Broker.instance().publish(new ProductPriceAdded(success));
	}
}
