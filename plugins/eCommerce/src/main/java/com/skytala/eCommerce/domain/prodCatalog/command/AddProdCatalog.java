package com.skytala.eCommerce.domain.prodCatalog.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.prodCatalog.event.ProdCatalogAdded;
import com.skytala.eCommerce.domain.prodCatalog.mapper.ProdCatalogMapper;
import com.skytala.eCommerce.domain.prodCatalog.model.ProdCatalog;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddProdCatalog extends Command {

	private ProdCatalog elementToBeAdded;

	public AddProdCatalog(ProdCatalog elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		ProdCatalog addedCatalog = null;
		try {
			elementToBeAdded.setProdCatalogId(delegator.getNextSeqId("ProdCatalog"));
			GenericValue newValue = delegator.makeValue("ProdCatalog", elementToBeAdded.mapAttributeField());
			addedCatalog = ProdCatalogMapper.map(delegator.create(newValue));
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}

		Event resultingEvent = new ProdCatalogAdded(addedCatalog, success);
		Broker.instance().publish(resultingEvent);
		return resultingEvent;
	}
}
