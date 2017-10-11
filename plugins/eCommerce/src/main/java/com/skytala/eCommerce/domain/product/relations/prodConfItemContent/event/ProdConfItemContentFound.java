package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;
public class ProdConfItemContentFound implements Event{

	private List<ProdConfItemContent> prodConfItemContents;

	public ProdConfItemContentFound(List<ProdConfItemContent> prodConfItemContents) {
		this.prodConfItemContents = prodConfItemContents;
	}

	public List<ProdConfItemContent> getProdConfItemContents()	{
		return prodConfItemContents;
	}

}
