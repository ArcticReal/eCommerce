package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;
public class ProdConfItemContentDeleted implements Event{

	private boolean success;

	public ProdConfItemContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
