package com.skytala.eCommerce.command;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.event.ProductPriceDeleted;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class DeleteProductPrice implements Command {

	private String toBeDeletedId;

	public DeleteProductPrice(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}

	@Override
	public void execute() throws RecordNotFoundException {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success = false;

		try {
			int countRemoved = delegator.removeByAnd("ProductPrice", UtilMisc.toMap("productPriceId", toBeDeletedId));
			if (countRemoved > 0) {
				success = true;
			} else {
				throw new RecordNotFoundException(ProductPrice.class);
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProductPriceDeleted(success));

	}

	public String getToBeDeletedId() {
		return toBeDeletedId;
	}

	public void setToBeDeletedId(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}
}
