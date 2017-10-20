package com.skytala.eCommerce.domain.accounting.relations.finAccount.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role.FinAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.role.FinAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.role.FinAccountRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountRole extends Command {

private FinAccountRole elementToBeAdded;
public AddFinAccountRole(FinAccountRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FinAccountRole", elementToBeAdded.mapAttributeField());
addedElement = FinAccountRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
