package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.query;
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
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.event.WorkEffortFixedAssetAssignAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.event.WorkEffortFixedAssetAssignFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.mapper.WorkEffortFixedAssetAssignMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.model.WorkEffortFixedAssetAssign;

public class FindWorkEffortFixedAssetAssignsBy extends Query {


Map<String, String> filter;
public FindWorkEffortFixedAssetAssignsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortFixedAssetAssign> foundWorkEffortFixedAssetAssigns = new ArrayList<WorkEffortFixedAssetAssign>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("workEffortFixedAssetAssignId")) { 
 GenericValue foundElement = delegator.findOne("WorkEffortFixedAssetAssign", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(WorkEffortFixedAssetAssign.class); 
 } 
}else { 
 buf = delegator.findAll("WorkEffortFixedAssetAssign", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundWorkEffortFixedAssetAssigns.add(WorkEffortFixedAssetAssignMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new WorkEffortFixedAssetAssignFound(foundWorkEffortFixedAssetAssigns);
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
