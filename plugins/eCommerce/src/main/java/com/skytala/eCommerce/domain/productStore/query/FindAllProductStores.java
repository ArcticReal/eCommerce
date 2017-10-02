
package com.skytala.eCommerce.domain.productStore.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productStore.event.ProductStoreFound;
import com.skytala.eCommerce.domain.productStore.mapper.ProductStoreMapper;
import com.skytala.eCommerce.domain.productStore.model.ProductStore;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllProductStores extends Query {

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProductStore> returnVal = new LinkedList<ProductStore>();
		try {
			List<GenericValue> results = delegator.findAll("ProductStore", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(ProductStoreMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProductStoreFound(returnVal));
		return null;
	}
}