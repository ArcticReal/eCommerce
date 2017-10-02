package com.skytala.eCommerce.domain.prodCatalog.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.prodCatalog.event.ProdCatalogFound;
import com.skytala.eCommerce.domain.prodCatalog.mapper.ProdCatalogMapper;
import com.skytala.eCommerce.domain.prodCatalog.model.ProdCatalog;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindProdCatalogsBy extends Query {

	Map<String, String> filter;

	public FindProdCatalogsBy(Map<String, String> filter) {
		this.filter = filter;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProdCatalog> foundProdCatalogs = new ArrayList<ProdCatalog>();

		try {
			List<GenericValue> buf = new LinkedList<>();
			if (filter.size() == 1 && filter.containsKey("prodCatalogId")) {
				GenericValue foundElement = delegator.findOne("ProdCatalog", false, filter);
				if (foundElement != null) {
					buf.add(foundElement);
				} else {
					throw new RecordNotFoundException(ProdCatalog.class);
				}
			} else {
				buf = delegator.findAll("ProdCatalog", false);
			}

			for (int i = 0; i < buf.size(); i++) {
				if (applysToFilter(buf.get(i))) {
					foundProdCatalogs.add(ProdCatalogMapper.map(buf.get(i)));
				}
			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new ProdCatalogFound(foundProdCatalogs));
		return null;

	}

	public boolean applysToFilter(GenericValue val) {

		Iterator<String> iterator = filter.keySet().iterator();

		while (iterator.hasNext()) {

			String key = iterator.next();

			if (val.get(key) == null) {
				return false;
			}

			if ((val.get(key).toString()).contains(filter.get(key))) {
			} else {
				return false;
			}
		}
		return true;
	}
	
	public void setFilter(Map<String, String> filter) {
		this.filter = filter;
	}

}
