package com.skytala.eCommerce.domain.accounting.relations.eftAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.event.EftAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.mapper.EftAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEftAccount extends Command {

private EftAccount elementToBeAdded;
public AddEftAccount(EftAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EftAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("EftAccount", elementToBeAdded.mapAttributeField());
addedElement = EftAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EftAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
