package com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.event.CreditCardTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.mapper.CreditCardTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.creditCardTypeGlAccount.model.CreditCardTypeGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCreditCardTypeGlAccount extends Command {

private CreditCardTypeGlAccount elementToBeAdded;
public AddCreditCardTypeGlAccount(CreditCardTypeGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CreditCardTypeGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CreditCardTypeGlAccount", elementToBeAdded.mapAttributeField());
addedElement = CreditCardTypeGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CreditCardTypeGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
