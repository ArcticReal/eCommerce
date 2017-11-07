package com.skytala.eCommerce.domain.login.relations.securityPermission.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionAdded;
import com.skytala.eCommerce.domain.login.relations.securityPermission.mapper.SecurityPermissionMapper;
import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSecurityPermission extends Command {

private SecurityPermission elementToBeAdded;
public AddSecurityPermission(SecurityPermission elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SecurityPermission addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPermissionId(delegator.getNextSeqId("SecurityPermission"));
GenericValue newValue = delegator.makeValue("SecurityPermission", elementToBeAdded.mapAttributeField());
addedElement = SecurityPermissionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SecurityPermissionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
