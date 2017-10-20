package com.skytala.eCommerce.domain.accounting.relations.budget.command.revision;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revision.BudgetRevisionUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.revision.BudgetRevision;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetRevision extends Command {

private BudgetRevision elementToBeUpdated;

public UpdateBudgetRevision(BudgetRevision elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetRevision getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetRevision elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetRevision", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetRevision.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetRevision.class);
}
success = false;
}
Event resultingEvent = new BudgetRevisionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
