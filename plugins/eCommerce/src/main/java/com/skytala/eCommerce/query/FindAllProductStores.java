
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductStore;
import com.skytala.eCommerce.entity.ProductStoreMapper;
import com.skytala.eCommerce.event.ProductStoreFound;

public class FindAllProductStores implements Query {

	@Override
	public void execute() {

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
	}
}
