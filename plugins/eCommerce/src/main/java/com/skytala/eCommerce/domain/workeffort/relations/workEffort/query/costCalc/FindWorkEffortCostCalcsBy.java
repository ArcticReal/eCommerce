package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.costCalc;
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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.costCalc.WorkEffortCostCalcMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.costCalc.WorkEffortCostCalc;

public class FindWorkEffortCostCalcsBy extends Query {


Map<String, String> filter;
public FindWorkEffortCostCalcsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortCostCalc> foundWorkEffortCostCalcs = new ArrayList<WorkEffortCostCalc>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("workEffortCostCalcId")) { 
 GenericValue foundElement = delegator.findOne("WorkEffortCostCalc", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(WorkEffortCostCalc.class); 
 } 
}else { 
 buf = delegator.findAll("WorkEffortCostCalc", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundWorkEffortCostCalcs.add(WorkEffortCostCalcMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new WorkEffortCostCalcFound(foundWorkEffortCostCalcs);
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
