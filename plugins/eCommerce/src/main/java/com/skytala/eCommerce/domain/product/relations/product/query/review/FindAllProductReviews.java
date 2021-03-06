
package com.skytala.eCommerce.domain.product.relations.product.query.review;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.review.ProductReviewFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.review.ProductReviewMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.review.ProductReview;


public class FindAllProductReviews extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductReview> returnVal = new ArrayList<ProductReview>();
try{
List<GenericValue> results = delegator.findAll("ProductReview", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductReviewMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductReviewFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
