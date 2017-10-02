package com.skytala.eCommerce.domain.productStore.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productStore.event.ProductStoreAdded;
import com.skytala.eCommerce.domain.productStore.model.ProductStore;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddProductStore extends Command {

	private ProductStore elementToBeAdded;

	public AddProductStore(ProductStore elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			elementToBeAdded.setProductStoreId(delegator.getNextSeqId("ProductStore"));
			GenericValue newValue = delegator.makeValue("ProductStore", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}

		Broker.instance().publish(new ProductStoreAdded(elementToBeAdded, success));
		return null;
	}
}
