package com.skytala.eCommerce.domain.login.relations.securityGroup.command.permission;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission.SecurityGroupPermissionAdded;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.permission.SecurityGroupPermissionMapper;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission.SecurityGroupPermission;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSecurityGroupPermission extends Command {

private SecurityGroupPermission elementToBeAdded;
public AddSecurityGroupPermission(SecurityGroupPermission elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SecurityGroupPermission addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPermissionId(delegator.getNextSeqId("SecurityGroupPermission"));
GenericValue newValue = delegator.makeValue("SecurityGroupPermission", elementToBeAdded.mapAttributeField());
addedElement = SecurityGroupPermissionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SecurityGroupPermissionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
