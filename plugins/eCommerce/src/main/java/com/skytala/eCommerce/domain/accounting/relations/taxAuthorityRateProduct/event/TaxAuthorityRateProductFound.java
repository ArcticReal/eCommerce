package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateProduct.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityRateProduct.model.TaxAuthorityRateProduct;
public class TaxAuthorityRateProductFound implements Event{

	private List<TaxAuthorityRateProduct> taxAuthorityRateProducts;

	public TaxAuthorityRateProductFound(List<TaxAuthorityRateProduct> taxAuthorityRateProducts) {
		this.taxAuthorityRateProducts = taxAuthorityRateProducts;
	}

	public List<TaxAuthorityRateProduct> getTaxAuthorityRateProducts()	{
		return taxAuthorityRateProducts;
	}

}
