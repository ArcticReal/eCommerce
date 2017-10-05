package com.skytala.eCommerce.domain.accommodationClass.query;
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
import com.skytala.eCommerce.domain.accommodationClass.event.AccommodationClassAdded;
import com.skytala.eCommerce.domain.accommodationClass.event.AccommodationClassFound;
import com.skytala.eCommerce.domain.accommodationClass.mapper.AccommodationClassMapper;
import com.skytala.eCommerce.domain.accommodationClass.model.AccommodationClass;

public class FindAccommodationClasssBy extends Query {


Map<String, String> filter;
public FindAccommodationClasssBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AccommodationClass> foundAccommodationClasss = new ArrayList<AccommodationClass>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("accommodationClassId")) { 
 GenericValue foundElement = delegator.findOne("AccommodationClass", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(AccommodationClass.class); 
 } 
}else { 
 buf = delegator.findAll("AccommodationClass", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundAccommodationClasss.add(AccommodationClassMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new AccommodationClassFound(foundAccommodationClasss);
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
