package com.skytala.eCommerce.domain.accounting.relations.budget.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.item.BudgetItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetItem extends Command {

private BudgetItem elementToBeUpdated;

public UpdateBudgetItem(BudgetItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetItem.class);
}
success = false;
}
Event resultingEvent = new BudgetItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
