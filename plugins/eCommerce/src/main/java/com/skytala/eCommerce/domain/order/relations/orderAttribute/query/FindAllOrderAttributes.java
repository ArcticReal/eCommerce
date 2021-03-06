
package com.skytala.eCommerce.domain.order.relations.orderAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.event.OrderAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.mapper.OrderAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAttribute.model.OrderAttribute;


public class FindAllOrderAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderAttribute> returnVal = new ArrayList<OrderAttribute>();
try{
List<GenericValue> results = delegator.findAll("OrderAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
