package com.skytala.eCommerce.domain.productPromo.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productPromo.event.ProductPromoAdded;
import com.skytala.eCommerce.domain.productPromo.model.ProductPromo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddProductPromo extends Command {

	private ProductPromo elementToBeAdded;

	public AddProductPromo(ProductPromo elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			elementToBeAdded.setProductPromoId(delegator.getNextSeqId("ProductPromo"));
			GenericValue newValue = delegator.makeValue("ProductPromo", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}

		Broker.instance().publish(new ProductPromoAdded(success));
		return null;
	}
}
