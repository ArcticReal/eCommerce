package com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.query;
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
import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.event.CustRequestItemWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.event.CustRequestItemWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.mapper.CustRequestItemWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.model.CustRequestItemWorkEffort;

public class FindCustRequestItemWorkEffortsBy extends Query {


Map<String, String> filter;
public FindCustRequestItemWorkEffortsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestItemWorkEffort> foundCustRequestItemWorkEfforts = new ArrayList<CustRequestItemWorkEffort>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("custRequestItemWorkEffortId")) { 
 GenericValue foundElement = delegator.findOne("CustRequestItemWorkEffort", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(CustRequestItemWorkEffort.class); 
 } 
}else { 
 buf = delegator.findAll("CustRequestItemWorkEffort", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundCustRequestItemWorkEfforts.add(CustRequestItemWorkEffortMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new CustRequestItemWorkEffortFound(foundCustRequestItemWorkEfforts);
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
