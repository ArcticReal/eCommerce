package com.skytala.eCommerce.domain.party.relations.contactMech.command.validRole;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.validRole.ValidContactMechRoleAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.validRole.ValidContactMechRoleMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.validRole.ValidContactMechRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddValidContactMechRole extends Command {

private ValidContactMechRole elementToBeAdded;
public AddValidContactMechRole(ValidContactMechRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ValidContactMechRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ValidContactMechRole", elementToBeAdded.mapAttributeField());
addedElement = ValidContactMechRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ValidContactMechRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
