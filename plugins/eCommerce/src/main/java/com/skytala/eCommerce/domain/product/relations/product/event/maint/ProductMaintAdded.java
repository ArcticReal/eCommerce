package com.skytala.eCommerce.domain.product.relations.product.event.maint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.maint.ProductMaint;
public class ProductMaintAdded implements Event{

	private ProductMaint addedProductMaint;
	private boolean success;

	public ProductMaintAdded(ProductMaint addedProductMaint, boolean success){
		this.addedProductMaint = addedProductMaint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductMaint getAddedProductMaint() {
		return addedProductMaint;
	}

}
