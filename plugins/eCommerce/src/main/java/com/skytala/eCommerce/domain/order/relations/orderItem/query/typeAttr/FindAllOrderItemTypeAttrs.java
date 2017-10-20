
package com.skytala.eCommerce.domain.order.relations.orderItem.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.typeAttr.OrderItemTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.typeAttr.OrderItemTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.typeAttr.OrderItemTypeAttr;


public class FindAllOrderItemTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemTypeAttr> returnVal = new ArrayList<OrderItemTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("OrderItemTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
