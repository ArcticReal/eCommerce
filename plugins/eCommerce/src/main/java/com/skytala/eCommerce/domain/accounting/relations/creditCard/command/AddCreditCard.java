package com.skytala.eCommerce.domain.accounting.relations.creditCard.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.event.CreditCardAdded;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.mapper.CreditCardMapper;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCreditCard extends Command {

private CreditCard elementToBeAdded;
public AddCreditCard(CreditCard elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CreditCard addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CreditCard", elementToBeAdded.mapAttributeField());
addedElement = CreditCardMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CreditCardAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
