package com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.typeClass;
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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.typeClass.EmplPositionTypeClassMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeClass.EmplPositionTypeClass;

public class FindEmplPositionTypeClasssBy extends Query {


Map<String, String> filter;
public FindEmplPositionTypeClasssBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPositionTypeClass> foundEmplPositionTypeClasss = new ArrayList<EmplPositionTypeClass>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("emplPositionTypeClassId")) { 
 GenericValue foundElement = delegator.findOne("EmplPositionTypeClass", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(EmplPositionTypeClass.class); 
 } 
}else { 
 buf = delegator.findAll("EmplPositionTypeClass", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundEmplPositionTypeClasss.add(EmplPositionTypeClassMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new EmplPositionTypeClassFound(foundEmplPositionTypeClasss);
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
