
package com.skytala.eCommerce.domain.order.relations.orderRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderRole.event.OrderRoleFound;
import com.skytala.eCommerce.domain.order.relations.orderRole.mapper.OrderRoleMapper;
import com.skytala.eCommerce.domain.order.relations.orderRole.model.OrderRole;


public class FindAllOrderRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderRole> returnVal = new ArrayList<OrderRole>();
try{
List<GenericValue> results = delegator.findAll("OrderRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
