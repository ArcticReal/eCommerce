package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.type.ProdConfItemContentType;
public class ProdConfItemContentTypeDeleted implements Event{

	private boolean success;

	public ProdConfItemContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
