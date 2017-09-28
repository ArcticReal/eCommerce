
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPromo;
import com.skytala.eCommerce.entity.ProductPromoMapper;
import com.skytala.eCommerce.event.ProductPromoFound;

public class FindAllProductPromos implements Query {

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProductPromo> returnVal = new LinkedList<ProductPromo>();
		try {
			List<GenericValue> results = delegator.findAll("ProductPromo", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(ProductPromoMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProductPromoFound(returnVal));
	}
}
