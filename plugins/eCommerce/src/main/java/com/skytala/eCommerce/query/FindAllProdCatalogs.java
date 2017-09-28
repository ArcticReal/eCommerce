
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProdCatalog;
import com.skytala.eCommerce.entity.ProdCatalogMapper;
import com.skytala.eCommerce.event.ProdCatalogFound;

public class FindAllProdCatalogs implements Query {

	@Override
	public void execute() {

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
	}
}
