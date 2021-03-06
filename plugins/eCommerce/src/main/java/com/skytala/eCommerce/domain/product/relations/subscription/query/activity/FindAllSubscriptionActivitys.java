
package com.skytala.eCommerce.domain.product.relations.subscription.query.activity;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscription.event.activity.SubscriptionActivityFound;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.activity.SubscriptionActivityMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.activity.SubscriptionActivity;


public class FindAllSubscriptionActivitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionActivity> returnVal = new ArrayList<SubscriptionActivity>();
try{
List<GenericValue> results = delegator.findAll("SubscriptionActivity", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionActivityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionActivityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
