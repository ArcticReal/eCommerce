package com.skytala.eCommerce.domain.productPrice.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productPrice.event.ProductPriceFound;
import com.skytala.eCommerce.domain.productPrice.mapper.ProductPriceMapper;
import com.skytala.eCommerce.domain.productPrice.model.ProductPrice;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindProductPricesBy extends Query {

	Map<String, String> filter;

	public FindProductPricesBy(Map<String, String> filter) {
		this.filter = filter;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProductPrice> foundProductPrices = new ArrayList<ProductPrice>();

		try {
			List<GenericValue> buf = new LinkedList<>();
			if (filter.size() == 1 && filter.containsKey("productPriceId")) {
				GenericValue foundElement = delegator.findOne("ProductPrice", false, filter);
				if (foundElement != null) {
					buf.add(foundElement);
				} else {
					throw new RecordNotFoundException(ProductPrice.class);
				}
			} else {
				buf = delegator.findAll("ProductPrice", false);
			}

			for (int i = 0; i < buf.size(); i++) {
				if (applysToFilter(buf.get(i))) {
					foundProductPrices.add(ProductPriceMapper.map(buf.get(i)));
				}
			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		Broker.instance().publish(new ProductPriceFound(foundProductPrices));
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
