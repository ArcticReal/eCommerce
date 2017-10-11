package com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.model.SubscriptionFulfillmentPiece;
public class SubscriptionFulfillmentPieceFound implements Event{

	private List<SubscriptionFulfillmentPiece> subscriptionFulfillmentPieces;

	public SubscriptionFulfillmentPieceFound(List<SubscriptionFulfillmentPiece> subscriptionFulfillmentPieces) {
		this.subscriptionFulfillmentPieces = subscriptionFulfillmentPieces;
	}

	public List<SubscriptionFulfillmentPiece> getSubscriptionFulfillmentPieces()	{
		return subscriptionFulfillmentPieces;
	}

}
