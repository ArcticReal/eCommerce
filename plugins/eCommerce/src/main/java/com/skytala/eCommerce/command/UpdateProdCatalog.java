package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProdCatalog;
import com.skytala.eCommerce.event.ProdCatalogUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class UpdateProdCatalog implements Command {

	private ProdCatalog elementToBeUpdated;

	public UpdateProdCatalog(ProdCatalog elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	public ProdCatalog getElementToBeUpdated() {
		return elementToBeUpdated;
	}

	public void setElementToBeUpdated(ProdCatalog elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	@Override
	public void execute() throws RecordNotFoundException {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			GenericValue newValue = delegator.makeValue("ProdCatalog", elementToBeUpdated.mapAttributeField());
			delegator.store(newValue);
			if (delegator.store(newValue) == 0) {
				throw new RecordNotFoundException(ProdCatalog.class);
			}
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}
		Broker.instance().publish(new ProdCatalogUpdated(success));
	}
}
