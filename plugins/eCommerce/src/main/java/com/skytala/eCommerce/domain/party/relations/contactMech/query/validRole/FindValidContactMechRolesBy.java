package com.skytala.eCommerce.domain.party.relations.contactMech.query.validRole;
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
import com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole.ValidContactMechRoleFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.validRole.ValidContactMechRoleMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole.ValidContactMechRole;

public class FindValidContactMechRolesBy extends Query {


Map<String, String> filter;
public FindValidContactMechRolesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ValidContactMechRole> foundValidContactMechRoles = new ArrayList<ValidContactMechRole>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("validContactMechRoleId")) { 
 GenericValue foundElement = delegator.findOne("ValidContactMechRole", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(ValidContactMechRole.class); 
 } 
}else { 
 buf = delegator.findAll("ValidContactMechRole", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundValidContactMechRoles.add(ValidContactMechRoleMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new ValidContactMechRoleFound(foundValidContactMechRoles);
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
