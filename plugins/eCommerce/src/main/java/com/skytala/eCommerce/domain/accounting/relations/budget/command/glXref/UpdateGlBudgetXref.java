package com.skytala.eCommerce.domain.accounting.relations.budget.command.glXref;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref.GlBudgetXrefUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.glXref.GlBudgetXref;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlBudgetXref extends Command {

private GlBudgetXref elementToBeUpdated;

public UpdateGlBudgetXref(GlBudgetXref elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlBudgetXref getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlBudgetXref elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlBudgetXref", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlBudgetXref.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlBudgetXref.class);
}
success = false;
}
Event resultingEvent = new GlBudgetXrefUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
