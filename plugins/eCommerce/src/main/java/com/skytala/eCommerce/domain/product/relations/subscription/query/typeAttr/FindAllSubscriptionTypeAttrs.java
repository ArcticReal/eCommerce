
package com.skytala.eCommerce.domain.product.relations.subscription.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr.SubscriptionTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.typeAttr.SubscriptionTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.typeAttr.SubscriptionTypeAttr;


public class FindAllSubscriptionTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SubscriptionTypeAttr> returnVal = new ArrayList<SubscriptionTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("SubscriptionTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SubscriptionTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SubscriptionTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
