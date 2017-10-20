
package com.skytala.eCommerce.domain.order.relations.orderItem.query.shipGroupAssoc;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc.OrderItemShipGroupAssocFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGroupAssoc.OrderItemShipGroupAssocMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroupAssoc.OrderItemShipGroupAssoc;


public class FindAllOrderItemShipGroupAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemShipGroupAssoc> returnVal = new ArrayList<OrderItemShipGroupAssoc>();
try{
List<GenericValue> results = delegator.findAll("OrderItemShipGroupAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemShipGroupAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemShipGroupAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
