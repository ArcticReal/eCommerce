package com.skytala.eCommerce.domain.finAccountAuth.query;
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
import com.skytala.eCommerce.domain.finAccountAuth.event.FinAccountAuthAdded;
import com.skytala.eCommerce.domain.finAccountAuth.event.FinAccountAuthFound;
import com.skytala.eCommerce.domain.finAccountAuth.mapper.FinAccountAuthMapper;
import com.skytala.eCommerce.domain.finAccountAuth.model.FinAccountAuth;

public class FindFinAccountAuthsBy extends Query {


Map<String, String> filter;
public FindFinAccountAuthsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountAuth> foundFinAccountAuths = new ArrayList<FinAccountAuth>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("finAccountAuthId")) { 
 GenericValue foundElement = delegator.findOne("FinAccountAuth", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(FinAccountAuth.class); 
 } 
}else { 
 buf = delegator.findAll("FinAccountAuth", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundFinAccountAuths.add(FinAccountAuthMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new FinAccountAuthFound(foundFinAccountAuths);
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
