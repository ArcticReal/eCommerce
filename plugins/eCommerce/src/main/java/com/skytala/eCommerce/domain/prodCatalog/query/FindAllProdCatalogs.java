
package com.skytala.eCommerce.domain.prodCatalog.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.prodCatalog.event.ProdCatalogFound;
import com.skytala.eCommerce.domain.prodCatalog.mapper.ProdCatalogMapper;
import com.skytala.eCommerce.domain.prodCatalog.model.ProdCatalog;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllProdCatalogs extends Query {

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProdCatalog> returnVal = new LinkedList<ProdCatalog>();
		try {
			List<GenericValue> results = delegator.findAll("ProdCatalog", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(ProdCatalogMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProdCatalogFound(returnVal));
		return null;
	}
}
