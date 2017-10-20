package com.skytala.eCommerce.domain.order.relations.requirement.query.workFulfillment;
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
import com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment.WorkRequirementFulfillmentFound;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.workFulfillment.WorkRequirementFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.workFulfillment.WorkRequirementFulfillment;

public class FindWorkRequirementFulfillmentsBy extends Query {


Map<String, String> filter;
public FindWorkRequirementFulfillmentsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkRequirementFulfillment> foundWorkRequirementFulfillments = new ArrayList<WorkRequirementFulfillment>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("workRequirementFulfillmentId")) { 
 GenericValue foundElement = delegator.findOne("WorkRequirementFulfillment", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(WorkRequirementFulfillment.class); 
 } 
}else { 
 buf = delegator.findAll("WorkRequirementFulfillment", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundWorkRequirementFulfillments.add(WorkRequirementFulfillmentMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new WorkRequirementFulfillmentFound(foundWorkRequirementFulfillments);
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
