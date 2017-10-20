package com.skytala.eCommerce.domain.order.relations.quote.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.quote.event.role.QuoteRoleUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.model.role.QuoteRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteRole extends Command {

private QuoteRole elementToBeUpdated;

public UpdateQuoteRole(QuoteRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteRole.class);
}
success = false;
}
Event resultingEvent = new QuoteRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
