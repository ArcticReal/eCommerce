package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;
public class ProdConfItemContentUpdated implements Event{

	private boolean success;

	public ProdConfItemContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
