
package com.skytala.eCommerce.domain.accounting.relations.budgetReview.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetReview.event.BudgetReviewFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetReview.mapper.BudgetReviewMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetReview.model.BudgetReview;


public class FindAllBudgetReviews extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetReview> returnVal = new ArrayList<BudgetReview>();
try{
List<GenericValue> results = delegator.findAll("BudgetReview", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetReviewMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetReviewFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
