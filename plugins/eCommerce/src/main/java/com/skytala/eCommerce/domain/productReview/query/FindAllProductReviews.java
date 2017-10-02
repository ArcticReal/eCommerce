
package com.skytala.eCommerce.domain.productReview.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productReview.event.ProductReviewFound;
import com.skytala.eCommerce.domain.productReview.mapper.ProductReviewMapper;
import com.skytala.eCommerce.domain.productReview.model.ProductReview;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllProductReviews extends Query {

	@Override
	public Event execute() {

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
		return null;
	}
}
