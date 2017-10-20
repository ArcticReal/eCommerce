
package com.skytala.eCommerce.domain.order.relations.orderItem.query.assocType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.assocType.OrderItemAssocTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.assocType.OrderItemAssocType;


public class FindAllOrderItemAssocTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemAssocType> returnVal = new ArrayList<OrderItemAssocType>();
try{
List<GenericValue> results = delegator.findAll("OrderItemAssocType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemAssocTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemAssocTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
