package com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.query;
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
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.event.ContactListCommStatusAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.event.ContactListCommStatusFound;
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.mapper.ContactListCommStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.model.ContactListCommStatus;

public class FindContactListCommStatussBy extends Query {


Map<String, String> filter;
public FindContactListCommStatussBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactListCommStatus> foundContactListCommStatuss = new ArrayList<ContactListCommStatus>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("contactListCommStatusId")) { 
 GenericValue foundElement = delegator.findOne("ContactListCommStatus", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ContactListCommStatus.class); 
 } 
}else { 
 buf = delegator.findAll("ContactListCommStatus", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundContactListCommStatuss.add(ContactListCommStatusMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ContactListCommStatusFound(foundContactListCommStatuss);
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
