
package com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.event.SubscriptionAttributeFound;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.mapper.SubscriptionAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.model.SubscriptionAttribute;


public class FindAllSubscriptionAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionAttribute> returnVal = new ArrayList<SubscriptionAttribute>();
try{
List<GenericValue> results = delegator.findAll("SubscriptionAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
