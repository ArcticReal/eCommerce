
package com.skytala.eCommerce.domain.order.relations.orderItem.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.type.OrderItemTypeFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.type.OrderItemTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.type.OrderItemType;


public class FindAllOrderItemTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemType> returnVal = new ArrayList<OrderItemType>();
try{
List<GenericValue> results = delegator.findAll("OrderItemType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
