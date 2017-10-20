package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct.TaxAuthorityRateProduct;
public class TaxAuthorityRateProductFound implements Event{

	private List<TaxAuthorityRateProduct> taxAuthorityRateProducts;

	public TaxAuthorityRateProductFound(List<TaxAuthorityRateProduct> taxAuthorityRateProducts) {
		this.taxAuthorityRateProducts = taxAuthorityRateProducts;
	}

	public List<TaxAuthorityRateProduct> getTaxAuthorityRateProducts()	{
		return taxAuthorityRateProducts;
	}

}
