
package com.skytala.eCommerce.domain.order.relations.orderTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderTypeAttr.event.OrderTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.orderTypeAttr.mapper.OrderTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.orderTypeAttr.model.OrderTypeAttr;


public class FindAllOrderTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderTypeAttr> returnVal = new ArrayList<OrderTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("OrderTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
