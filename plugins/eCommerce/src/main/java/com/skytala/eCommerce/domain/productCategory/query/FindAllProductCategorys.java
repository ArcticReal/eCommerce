
package com.skytala.eCommerce.domain.productCategory.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productCategory.event.ProductCategoryFound;
import com.skytala.eCommerce.domain.productCategory.mapper.ProductCategoryMapper;
import com.skytala.eCommerce.domain.productCategory.model.ProductCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllProductCategorys extends Query {

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProductCategory> returnVal = new LinkedList<ProductCategory>();
		try {
			List<GenericValue> results = delegator.findAll("ProductCategory", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(ProductCategoryMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProductCategoryFound(returnVal));
		return null;
	}
}
