
package com.skytala.eCommerce.domain.login.relations.securityGroup.query.permission;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission.SecurityGroupPermissionFound;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.permission.SecurityGroupPermissionMapper;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission.SecurityGroupPermission;


public class FindAllSecurityGroupPermissions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SecurityGroupPermission> returnVal = new ArrayList<SecurityGroupPermission>();
try{
List<GenericValue> results = delegator.findAll("SecurityGroupPermission", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SecurityGroupPermissionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SecurityGroupPermissionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
