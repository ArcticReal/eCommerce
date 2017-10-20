package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.rateProduct;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct.TaxAuthorityRateProduct;
public class TaxAuthorityRateProductAdded implements Event{

	private TaxAuthorityRateProduct addedTaxAuthorityRateProduct;
	private boolean success;

	public TaxAuthorityRateProductAdded(TaxAuthorityRateProduct addedTaxAuthorityRateProduct, boolean success){
		this.addedTaxAuthorityRateProduct = addedTaxAuthorityRateProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TaxAuthorityRateProduct getAddedTaxAuthorityRateProduct() {
		return addedTaxAuthorityRateProduct;
	}

}
