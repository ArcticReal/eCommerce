package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.contactMech;
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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.contactMech.WorkEffortContactMechMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;

public class FindWorkEffortContactMechsBy extends Query {


Map<String, String> filter;
public FindWorkEffortContactMechsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortContactMech> foundWorkEffortContactMechs = new ArrayList<WorkEffortContactMech>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("workEffortContactMechId")) { 
 GenericValue foundElement = delegator.findOne("WorkEffortContactMech", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(WorkEffortContactMech.class); 
 } 
}else { 
 buf = delegator.findAll("WorkEffortContactMech", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundWorkEffortContactMechs.add(WorkEffortContactMechMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new WorkEffortContactMechFound(foundWorkEffortContactMechs);
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
