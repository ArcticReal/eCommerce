package com.skytala.eCommerce.domain.party.relations.telecomNumber.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.event.TelecomNumberAdded;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.mapper.TelecomNumberMapper;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTelecomNumber extends Command {

private TelecomNumber elementToBeAdded;
public AddTelecomNumber(TelecomNumber elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TelecomNumber addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("TelecomNumber", elementToBeAdded.mapAttributeField());
addedElement = TelecomNumberMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TelecomNumberAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
