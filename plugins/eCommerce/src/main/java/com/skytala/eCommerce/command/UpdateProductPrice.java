package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.entity.ProductPriceMapper;
import com.skytala.eCommerce.event.ProductPriceUpdated;

public class UpdateProductPrice implements Command {

	private ProductPrice productPriceToBeUpdated;

	public UpdateProductPrice(ProductPrice productPriceToBeUpdated) {
		this.productPriceToBeUpdated = productPriceToBeUpdated;
	}

	public ProductPrice getProductPriceToBeUpdated() {
		return productPriceToBeUpdated;
	}

	public void setProductPriceToBeUpdated(ProductPrice productPriceToBeUpdated) {
		this.productPriceToBeUpdated = productPriceToBeUpdated;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			GenericValue newValue = delegator.makeValue("Product", ProductPriceMapper.map(productPriceToBeUpdated));
			delegator.store(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		Broker.instance().publish(new ProductPriceUpdated(success));

	}

}
