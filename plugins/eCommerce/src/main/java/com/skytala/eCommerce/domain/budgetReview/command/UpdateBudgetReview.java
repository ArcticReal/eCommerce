package com.skytala.eCommerce.domain.budgetReview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.budgetReview.event.BudgetReviewUpdated;
import com.skytala.eCommerce.domain.budgetReview.model.BudgetReview;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetReview extends Command {

private BudgetReview elementToBeUpdated;

public UpdateBudgetReview(BudgetReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetReview getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetReview", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetReview.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetReview.class);
}
success = false;
}
Event resultingEvent = new BudgetReviewUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
