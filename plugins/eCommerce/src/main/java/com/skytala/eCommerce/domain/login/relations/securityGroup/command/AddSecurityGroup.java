package com.skytala.eCommerce.domain.login.relations.securityGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.SecurityGroupAdded;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.SecurityGroupMapper;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSecurityGroup extends Command {

private SecurityGroup elementToBeAdded;
public AddSecurityGroup(SecurityGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SecurityGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGroupId(delegator.getNextSeqId("SecurityGroup"));
GenericValue newValue = delegator.makeValue("SecurityGroup", elementToBeAdded.mapAttributeField());
addedElement = SecurityGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SecurityGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
