package com.skytala.eCommerce.domain.product.relations.subscription.event.fulfillmentPiece;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.fulfillmentPiece.SubscriptionFulfillmentPiece;
public class SubscriptionFulfillmentPieceDeleted implements Event{

	private boolean success;

	public SubscriptionFulfillmentPieceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
