package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.role.GlAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.role.GlAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.role.GlAccountRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlAccountRole extends Command {

private GlAccountRole elementToBeAdded;
public AddGlAccountRole(GlAccountRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlAccountRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("GlAccountRole"));
GenericValue newValue = delegator.makeValue("GlAccountRole", elementToBeAdded.mapAttributeField());
addedElement = GlAccountRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlAccountRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
