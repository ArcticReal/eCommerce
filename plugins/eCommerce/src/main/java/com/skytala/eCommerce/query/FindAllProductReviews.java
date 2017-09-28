
package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductReview;
import com.skytala.eCommerce.entity.ProductReviewMapper;
import com.skytala.eCommerce.event.ProductReviewFound;

public class FindAllProductReviews implements Query {

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		List<ProductReview> returnVal = new LinkedList<ProductReview>();
		try {
			List<GenericValue> results = delegator.findAll("ProductReview", false);
			for (int i = 0; i < results.size(); i++) {
				returnVal.add(ProductReviewMapper.map(results.get(i)));
			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
		}
		Broker.instance().publish(new ProductReviewFound(returnVal));
	}
}
