package com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.model.SubscriptionFulfillmentPiece;
public class SubscriptionFulfillmentPieceUpdated implements Event{

	private boolean success;

	public SubscriptionFulfillmentPieceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
