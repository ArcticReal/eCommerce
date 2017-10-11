package com.skytala.eCommerce.domain.party.relations.communicationEventProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.event.CommunicationEventProductAdded;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.mapper.CommunicationEventProductMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.model.CommunicationEventProduct;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommunicationEventProduct extends Command {

private CommunicationEventProduct elementToBeAdded;
public AddCommunicationEventProduct(CommunicationEventProduct elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommunicationEventProduct addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CommunicationEventProduct", elementToBeAdded.mapAttributeField());
addedElement = CommunicationEventProductMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommunicationEventProductAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
