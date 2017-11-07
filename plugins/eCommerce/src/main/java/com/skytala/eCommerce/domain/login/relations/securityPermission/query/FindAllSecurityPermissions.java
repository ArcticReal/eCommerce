
package com.skytala.eCommerce.domain.login.relations.securityPermission.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionFound;
import com.skytala.eCommerce.domain.login.relations.securityPermission.mapper.SecurityPermissionMapper;
import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;


public class FindAllSecurityPermissions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SecurityPermission> returnVal = new ArrayList<SecurityPermission>();
try{
List<GenericValue> results = delegator.findAll("SecurityPermission", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SecurityPermissionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SecurityPermissionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
