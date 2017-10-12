
package com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.event.BudgetRevisionImpactFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.mapper.BudgetRevisionImpactMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.model.BudgetRevisionImpact;


public class FindAllBudgetRevisionImpacts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetRevisionImpact> returnVal = new ArrayList<BudgetRevisionImpact>();
try{
List<GenericValue> results = delegator.findAll("BudgetRevisionImpact", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetRevisionImpactMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetRevisionImpactFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
