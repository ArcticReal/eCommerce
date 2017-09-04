package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.entity.ProductPriceMapper;
import com.skytala.eCommerce.event.ProductPriceAdded;

public class AddProductPrice implements Command {

	ProductPrice productPriceToBeAdded;

	public AddProductPrice(ProductPrice productPriceToBeAdded) {
		this.productPriceToBeAdded = productPriceToBeAdded;
	}

	public ProductPrice getProductPriceToBeAdded() {
		return productPriceToBeAdded;
	}

	public void setProductPriceToBeAdded(ProductPrice productPriceToBeAdded) {
		this.productPriceToBeAdded = productPriceToBeAdded;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;

		try {
			GenericValue newValue = delegator.makeValue("ProductPrice", ProductPriceMapper.map(productPriceToBeAdded));
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		Broker.instance().publish(new ProductPriceAdded(success));

	}

}
