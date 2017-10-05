package com.skytala.eCommerce.domain.orderItemAssocType.query;
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
import com.skytala.eCommerce.domain.orderItemAssocType.event.OrderItemAssocTypeAdded;
import com.skytala.eCommerce.domain.orderItemAssocType.event.OrderItemAssocTypeFound;
import com.skytala.eCommerce.domain.orderItemAssocType.mapper.OrderItemAssocTypeMapper;
import com.skytala.eCommerce.domain.orderItemAssocType.model.OrderItemAssocType;

public class FindOrderItemAssocTypesBy extends Query {


Map<String, String> filter;
public FindOrderItemAssocTypesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemAssocType> foundOrderItemAssocTypes = new ArrayList<OrderItemAssocType>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("orderItemAssocTypeId")) { 
 GenericValue foundElement = delegator.findOne("OrderItemAssocType", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(OrderItemAssocType.class); 
 } 
}else { 
 buf = delegator.findAll("OrderItemAssocType", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundOrderItemAssocTypes.add(OrderItemAssocTypeMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new OrderItemAssocTypeFound(foundOrderItemAssocTypes);
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
