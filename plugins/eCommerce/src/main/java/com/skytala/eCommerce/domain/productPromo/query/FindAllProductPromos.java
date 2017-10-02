
package com.skytala.eCommerce.domain.productPromo.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productPromo.event.ProductPromoFound;
import com.skytala.eCommerce.domain.productPromo.mapper.ProductPromoMapper;
import com.skytala.eCommerce.domain.productPromo.model.ProductPromo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllProductPromos extends Query {

	@Override
	public Event execute() {

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
		return null;
	}
}
