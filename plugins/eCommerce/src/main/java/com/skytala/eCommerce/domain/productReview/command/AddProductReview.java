package com.skytala.eCommerce.domain.productReview.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productReview.event.ProductReviewAdded;
import com.skytala.eCommerce.domain.productReview.model.ProductReview;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddProductReview extends Command {

	private ProductReview elementToBeAdded;

	public AddProductReview(ProductReview elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		ProductReview addedReview = null;
		try {
			elementToBeAdded.setProductReviewId(delegator.getNextSeqId("ProductReview"));
			GenericValue newValue = delegator.makeValue("ProductReview", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}

		Event resultingEvent = new ProductReviewAdded(addedReview, success);
		Broker.instance().publish(resultingEvent);
		return resultingEvent;
	}
}
