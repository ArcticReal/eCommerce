package com.skytala.eCommerce.domain.order.relations.orderItemChange.query;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderItemChange.event.OrderItemChangeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemChange.event.OrderItemChangeFound;
import com.skytala.eCommerce.domain.order.relations.orderItemChange.mapper.OrderItemChangeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemChange.model.OrderItemChange;

public class FindOrderItemChangesBy extends Query {


Map<String, String> filter;
public FindOrderItemChangesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemChange> foundOrderItemChanges = new ArrayList<OrderItemChange>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("orderItemChangeId")) { 
 GenericValue foundElement = delegator.findOne("OrderItemChange", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(OrderItemChange.class); 
 } 
}else { 
 buf = delegator.findAll("OrderItemChange", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundOrderItemChanges.add(OrderItemChangeMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new OrderItemChangeFound(foundOrderItemChanges);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public boolean applysToFilter(GenericValue val) {

Iterator<String> iterator = filter.keySet().iterator();

while(iterator.hasNext()) {

String key = iterator.next();

if(val.get(key) == null) {
return false;
}

if((val.get(key).toString()).contains(filter.get(key))) {
}else {
return false;
}
}
return true;
}
public void setFilter(Map<String, String> newFilter) {
this.filter = newFilter;
}
}