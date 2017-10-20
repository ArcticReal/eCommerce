package com.skytala.eCommerce.domain.product.relations.subscription.event.fulfillmentPiece;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.fulfillmentPiece.SubscriptionFulfillmentPiece;
public class SubscriptionFulfillmentPieceAdded implements Event{

	private SubscriptionFulfillmentPiece addedSubscriptionFulfillmentPiece;
	private boolean success;

	public SubscriptionFulfillmentPieceAdded(SubscriptionFulfillmentPiece addedSubscriptionFulfillmentPiece, boolean success){
		this.addedSubscriptionFulfillmentPiece = addedSubscriptionFulfillmentPiece;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SubscriptionFulfillmentPiece getAddedSubscriptionFulfillmentPiece() {
		return addedSubscriptionFulfillmentPiece;
	}

}
