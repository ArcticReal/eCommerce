package com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.event.FinAccountAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.mapper.FinAccountAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.model.FinAccountAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountAttribute extends Command {

private FinAccountAttribute elementToBeAdded;
public AddFinAccountAttribute(FinAccountAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FinAccountAttribute", elementToBeAdded.mapAttributeField());
addedElement = FinAccountAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
