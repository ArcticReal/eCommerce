
package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event.CartAbandonedLineFound;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.mapper.CartAbandonedLineMapper;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;


public class FindAllCartAbandonedLines extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CartAbandonedLine> returnVal = new ArrayList<CartAbandonedLine>();
try{
List<GenericValue> results = delegator.findAll("CartAbandonedLine", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CartAbandonedLineMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CartAbandonedLineFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
