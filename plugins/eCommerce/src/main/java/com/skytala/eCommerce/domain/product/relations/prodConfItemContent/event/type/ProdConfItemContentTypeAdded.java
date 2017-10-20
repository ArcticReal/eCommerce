package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.type.ProdConfItemContentType;
public class ProdConfItemContentTypeAdded implements Event{

	private ProdConfItemContentType addedProdConfItemContentType;
	private boolean success;

	public ProdConfItemContentTypeAdded(ProdConfItemContentType addedProdConfItemContentType, boolean success){
		this.addedProdConfItemContentType = addedProdConfItemContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProdConfItemContentType getAddedProdConfItemContentType() {
		return addedProdConfItemContentType;
	}

}
