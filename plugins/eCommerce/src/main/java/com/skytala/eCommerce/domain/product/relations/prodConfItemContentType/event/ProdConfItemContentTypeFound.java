package com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.model.ProdConfItemContentType;
public class ProdConfItemContentTypeFound implements Event{

	private List<ProdConfItemContentType> prodConfItemContentTypes;

	public ProdConfItemContentTypeFound(List<ProdConfItemContentType> prodConfItemContentTypes) {
		this.prodConfItemContentTypes = prodConfItemContentTypes;
	}

	public List<ProdConfItemContentType> getProdConfItemContentTypes()	{
		return prodConfItemContentTypes;
	}

}
