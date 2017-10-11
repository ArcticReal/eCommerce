
package com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.event.ProductSubscriptionResourceFound;
import com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.mapper.ProductSubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.model.ProductSubscriptionResource;


public class FindAllProductSubscriptionResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductSubscriptionResource> returnVal = new ArrayList<ProductSubscriptionResource>();
try{
List<GenericValue> results = delegator.findAll("ProductSubscriptionResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductSubscriptionResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductSubscriptionResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
