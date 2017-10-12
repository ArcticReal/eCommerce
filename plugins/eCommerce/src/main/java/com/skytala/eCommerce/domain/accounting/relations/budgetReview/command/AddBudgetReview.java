package com.skytala.eCommerce.domain.accounting.relations.budgetReview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetReview.event.BudgetReviewAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetReview.mapper.BudgetReviewMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetReview.model.BudgetReview;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetReview extends Command {

private BudgetReview elementToBeAdded;
public AddBudgetReview(BudgetReview elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetReview addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetReviewId(delegator.getNextSeqId("BudgetReview"));
GenericValue newValue = delegator.makeValue("BudgetReview", elementToBeAdded.mapAttributeField());
addedElement = BudgetReviewMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetReviewAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
