
package com.skytala.eCommerce.domain.accounting.relations.budget.query.reviewResultType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.reviewResultType.BudgetReviewResultTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.reviewResultType.BudgetReviewResultTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.reviewResultType.BudgetReviewResultType;


public class FindAllBudgetReviewResultTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetReviewResultType> returnVal = new ArrayList<BudgetReviewResultType>();
try{
List<GenericValue> results = delegator.findAll("BudgetReviewResultType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetReviewResultTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetReviewResultTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
