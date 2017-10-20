package com.skytala.eCommerce.domain.accounting.relations.budget.command.revisionImpact;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revisionImpact.BudgetRevisionImpactUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.revisionImpact.BudgetRevisionImpact;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetRevisionImpact extends Command {

private BudgetRevisionImpact elementToBeUpdated;

public UpdateBudgetRevisionImpact(BudgetRevisionImpact elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetRevisionImpact getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetRevisionImpact elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetRevisionImpact", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetRevisionImpact.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetRevisionImpact.class);
}
success = false;
}
Event resultingEvent = new BudgetRevisionImpactUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
