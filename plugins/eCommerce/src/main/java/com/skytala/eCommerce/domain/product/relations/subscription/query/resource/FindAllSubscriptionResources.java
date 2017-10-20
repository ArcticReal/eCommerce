
package com.skytala.eCommerce.domain.product.relations.subscription.query.resource;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscription.event.resource.SubscriptionResourceFound;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.resource.SubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.resource.SubscriptionResource;


public class FindAllSubscriptionResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionResource> returnVal = new ArrayList<SubscriptionResource>();
try{
List<GenericValue> results = delegator.findAll("SubscriptionResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
