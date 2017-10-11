
package com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.event.OrderItemShipGrpInvResFound;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.mapper.OrderItemShipGrpInvResMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.model.OrderItemShipGrpInvRes;


public class FindAllOrderItemShipGrpInvRess extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemShipGrpInvRes> returnVal = new ArrayList<OrderItemShipGrpInvRes>();
try{
List<GenericValue> results = delegator.findAll("OrderItemShipGrpInvRes", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemShipGrpInvResMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemShipGrpInvResFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
