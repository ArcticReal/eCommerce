
package com.skytala.eCommerce.domain.order.relations.orderShipment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderShipment.event.OrderShipmentFound;
import com.skytala.eCommerce.domain.order.relations.orderShipment.mapper.OrderShipmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;


public class FindAllOrderShipments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderShipment> returnVal = new ArrayList<OrderShipment>();
try{
List<GenericValue> results = delegator.findAll("OrderShipment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderShipmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderShipmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
