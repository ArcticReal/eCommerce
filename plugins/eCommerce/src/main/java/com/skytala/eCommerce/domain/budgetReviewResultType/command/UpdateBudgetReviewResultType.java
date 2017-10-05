package com.skytala.eCommerce.domain.budgetReviewResultType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.budgetReviewResultType.event.BudgetReviewResultTypeUpdated;
import com.skytala.eCommerce.domain.budgetReviewResultType.model.BudgetReviewResultType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetReviewResultType extends Command {

private BudgetReviewResultType elementToBeUpdated;

public UpdateBudgetReviewResultType(BudgetReviewResultType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetReviewResultType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetReviewResultType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetReviewResultType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetReviewResultType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetReviewResultType.class);
}
success = false;
}
Event resultingEvent = new BudgetReviewResultTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
