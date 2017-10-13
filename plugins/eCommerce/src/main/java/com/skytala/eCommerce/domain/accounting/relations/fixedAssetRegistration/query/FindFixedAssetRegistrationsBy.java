package com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.query;
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
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.event.FixedAssetRegistrationAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.event.FixedAssetRegistrationFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.mapper.FixedAssetRegistrationMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.model.FixedAssetRegistration;

public class FindFixedAssetRegistrationsBy extends Query {


Map<String, String> filter;
public FindFixedAssetRegistrationsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FixedAssetRegistration> foundFixedAssetRegistrations = new ArrayList<FixedAssetRegistration>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("fixedAssetRegistrationId")) { 
 GenericValue foundElement = delegator.findOne("FixedAssetRegistration", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(FixedAssetRegistration.class); 
 } 
}else { 
 buf = delegator.findAll("FixedAssetRegistration", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundFixedAssetRegistrations.add(FixedAssetRegistrationMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new FixedAssetRegistrationFound(foundFixedAssetRegistrations);
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