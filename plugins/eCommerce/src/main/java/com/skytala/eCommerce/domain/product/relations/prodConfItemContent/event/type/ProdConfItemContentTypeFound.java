package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.type.ProdConfItemContentType;
public class ProdConfItemContentTypeFound implements Event{

	private List<ProdConfItemContentType> prodConfItemContentTypes;

	public ProdConfItemContentTypeFound(List<ProdConfItemContentType> prodConfItemContentTypes) {
		this.prodConfItemContentTypes = prodConfItemContentTypes;
	}

	public List<ProdConfItemContentType> getProdConfItemContentTypes()	{
		return prodConfItemContentTypes;
	}

}
