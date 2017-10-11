package com.skytala.eCommerce.domain.order.relations.orderAdjustment.query;
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
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentAdded;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.OrderAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.OrderAdjustment;

public class FindOrderAdjustmentsBy extends Query {


Map<String, String> filter;
public FindOrderAdjustmentsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderAdjustment> foundOrderAdjustments = new ArrayList<OrderAdjustment>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("orderAdjustmentId")) { 
 GenericValue foundElement = delegator.findOne("OrderAdjustment", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(OrderAdjustment.class); 
 } 
}else { 
 buf = delegator.findAll("OrderAdjustment", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundOrderAdjustments.add(OrderAdjustmentMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new OrderAdjustmentFound(foundOrderAdjustments);
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
