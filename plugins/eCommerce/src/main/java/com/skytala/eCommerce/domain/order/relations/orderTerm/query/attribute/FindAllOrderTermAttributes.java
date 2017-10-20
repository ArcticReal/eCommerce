
package com.skytala.eCommerce.domain.order.relations.orderTerm.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.attribute.OrderTermAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.attribute.OrderTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.attribute.OrderTermAttribute;


public class FindAllOrderTermAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderTermAttribute> returnVal = new ArrayList<OrderTermAttribute>();
try{
List<GenericValue> results = delegator.findAll("OrderTermAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderTermAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderTermAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
