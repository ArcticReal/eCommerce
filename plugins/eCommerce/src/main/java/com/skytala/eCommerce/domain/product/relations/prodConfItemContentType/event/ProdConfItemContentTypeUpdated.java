package com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.model.ProdConfItemContentType;
public class ProdConfItemContentTypeUpdated implements Event{

	private boolean success;

	public ProdConfItemContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
