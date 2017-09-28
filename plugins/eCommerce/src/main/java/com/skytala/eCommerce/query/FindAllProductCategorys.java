
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductCategory;
import com.skytala.eCommerce.entity.ProductCategoryMapper;
import com.skytala.eCommerce.event.ProductCategoryFound;

public class FindAllProductCategorys implements Query {

	@Override
	public void execute() {

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
	}
}
