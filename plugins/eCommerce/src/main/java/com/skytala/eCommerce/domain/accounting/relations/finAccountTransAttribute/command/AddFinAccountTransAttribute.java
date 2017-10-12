package com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.event.FinAccountTransAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.mapper.FinAccountTransAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransAttribute.model.FinAccountTransAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountTransAttribute extends Command {

private FinAccountTransAttribute elementToBeAdded;
public AddFinAccountTransAttribute(FinAccountTransAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountTransAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FinAccountTransAttribute", elementToBeAdded.mapAttributeField());
addedElement = FinAccountTransAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountTransAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
