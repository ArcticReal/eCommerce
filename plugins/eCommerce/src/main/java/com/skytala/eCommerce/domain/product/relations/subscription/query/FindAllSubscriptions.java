
package com.skytala.eCommerce.domain.product.relations.subscription.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscription.event.SubscriptionFound;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.SubscriptionMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.Subscription;


public class FindAllSubscriptions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Subscription> returnVal = new ArrayList<Subscription>();
try{
List<GenericValue> results = delegator.findAll("Subscription", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
