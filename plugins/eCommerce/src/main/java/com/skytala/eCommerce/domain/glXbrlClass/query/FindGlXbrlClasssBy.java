package com.skytala.eCommerce.domain.glXbrlClass.query;
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
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassAdded;
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassFound;
import com.skytala.eCommerce.domain.glXbrlClass.mapper.GlXbrlClassMapper;
import com.skytala.eCommerce.domain.glXbrlClass.model.GlXbrlClass;

public class FindGlXbrlClasssBy extends Query {


Map<String, String> filter;
public FindGlXbrlClasssBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlXbrlClass> foundGlXbrlClasss = new ArrayList<GlXbrlClass>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("glXbrlClassId")) { 
 GenericValue foundElement = delegator.findOne("GlXbrlClass", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(GlXbrlClass.class); 
 } 
}else { 
 buf = delegator.findAll("GlXbrlClass", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundGlXbrlClasss.add(GlXbrlClassMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new GlXbrlClassFound(foundGlXbrlClasss);
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
