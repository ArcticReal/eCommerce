
package com.skytala.eCommerce.domain.order.relations.orderItemRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItemRole.event.OrderItemRoleFound;
import com.skytala.eCommerce.domain.order.relations.orderItemRole.mapper.OrderItemRoleMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemRole.model.OrderItemRole;


public class FindAllOrderItemRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemRole> returnVal = new ArrayList<OrderItemRole>();
try{
List<GenericValue> results = delegator.findAll("OrderItemRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
