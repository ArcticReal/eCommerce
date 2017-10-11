package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;
public class ProdConfItemContentAdded implements Event{

	private ProdConfItemContent addedProdConfItemContent;
	private boolean success;

	public ProdConfItemContentAdded(ProdConfItemContent addedProdConfItemContent, boolean success){
		this.addedProdConfItemContent = addedProdConfItemContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProdConfItemContent getAddedProdConfItemContent() {
		return addedProdConfItemContent;
	}

}
