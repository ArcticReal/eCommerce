package com.skytala.eCommerce.domain.product.relations.subscription.event.fulfillmentPiece;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.fulfillmentPiece.SubscriptionFulfillmentPiece;
public class SubscriptionFulfillmentPieceUpdated implements Event{

	private boolean success;

	public SubscriptionFulfillmentPieceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
