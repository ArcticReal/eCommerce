
package com.skytala.eCommerce.domain.productPrice.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productPrice.event.ProductPriceFound;
import com.skytala.eCommerce.domain.productPrice.mapper.ProductPriceMapper;
import com.skytala.eCommerce.domain.productPrice.model.ProductPrice;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllProductPrices extends Query {

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProductPrice> returnVal = new LinkedList<ProductPrice>();
		try {
			List<GenericValue> results = delegator.findAll("ProductPrice", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(ProductPriceMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProductPriceFound(returnVal));
		return null;
	}
}
