
package com.skytala.eCommerce.domain.order.relations.orderItemAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.event.OrderItemAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.mapper.OrderItemAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemAttribute.model.OrderItemAttribute;


public class FindAllOrderItemAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemAttribute> returnVal = new ArrayList<OrderItemAttribute>();
try{
List<GenericValue> results = delegator.findAll("OrderItemAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
