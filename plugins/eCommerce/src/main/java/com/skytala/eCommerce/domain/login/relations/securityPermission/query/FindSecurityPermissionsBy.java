package com.skytala.eCommerce.domain.login.relations.securityPermission.query;
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
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionFound;
import com.skytala.eCommerce.domain.login.relations.securityPermission.mapper.SecurityPermissionMapper;
import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;

public class FindSecurityPermissionsBy extends Query {


Map<String, String> filter;
public FindSecurityPermissionsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SecurityPermission> foundSecurityPermissions = new ArrayList<SecurityPermission>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("securityPermissionId")) { 
 GenericValue foundElement = delegator.findOne("SecurityPermission", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(SecurityPermission.class); 
 } 
}else { 
 buf = delegator.findAll("SecurityPermission", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundSecurityPermissions.add(SecurityPermissionMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new SecurityPermissionFound(foundSecurityPermissions);
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
