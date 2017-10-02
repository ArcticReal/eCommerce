package com.skytala.eCommerce.command;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductStore;
import com.skytala.eCommerce.event.ProductStoreDeleted;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class DeleteProductStore implements Command {

	private String toBeDeletedId;

	public DeleteProductStore(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}

	@Override
	public void execute() throws RecordNotFoundException {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success = false;

		try {
			int countRemoved = delegator.removeByAnd("ProductStore", UtilMisc.toMap("productStoreId", toBeDeletedId));
			if (countRemoved > 0) {
				success = true;
			} else {
				throw new RecordNotFoundException(ProductStore.class);
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProductStoreDeleted(success));

	}

	public String getToBeDeletedId() {
		return toBeDeletedId;
	}

	public void setToBeDeletedId(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}
}
