package com.skytala.eCommerce.domain.party.relations.communicationEvent.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.role.CommunicationEventRoleAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.role.CommunicationEventRoleMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.role.CommunicationEventRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEventRole extends Command {

private CommunicationEventRole elementToBeAdded;
public AddCommunicationEventRole(CommunicationEventRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEventRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("CommunicationEventRole"));
GenericValue newValue = delegator.makeValue("CommunicationEventRole", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
