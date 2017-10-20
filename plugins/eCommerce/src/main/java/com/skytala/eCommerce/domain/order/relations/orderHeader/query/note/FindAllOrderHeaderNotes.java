
package com.skytala.eCommerce.domain.order.relations.orderHeader.query.note;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.note.OrderHeaderNoteFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.note.OrderHeaderNoteMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.note.OrderHeaderNote;


public class FindAllOrderHeaderNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderHeaderNote> returnVal = new ArrayList<OrderHeaderNote>();
try{
List<GenericValue> results = delegator.findAll("OrderHeaderNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderHeaderNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderHeaderNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
