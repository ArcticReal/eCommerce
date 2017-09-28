package com.skytala.eCommerce.command;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProdCatalog;
import com.skytala.eCommerce.event.ProdCatalogDeleted;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class DeleteProdCatalog implements Command {

	private String toBeDeletedId;

	public DeleteProdCatalog(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}

	@Override
	public void execute() throws RecordNotFoundException {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success = false;

		try {
			int countRemoved = delegator.removeByAnd("ProdCatalog", UtilMisc.toMap("prodCatalogId", toBeDeletedId));
			if (countRemoved > 0) {
				success = true;
			} else {
				throw new RecordNotFoundException(ProdCatalog.class);
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProdCatalogDeleted(success));

	}

	public String getToBeDeletedId() {
		return toBeDeletedId;
	}

	public void setToBeDeletedId(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}
}
