
package com.skytala.eCommerce.domain.accounting.relations.budget.query.revision;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.revision.BudgetRevisionFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.revision.BudgetRevisionMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.revision.BudgetRevision;


public class FindAllBudgetRevisions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetRevision> returnVal = new ArrayList<BudgetRevision>();
try{
List<GenericValue> results = delegator.findAll("BudgetRevision", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetRevisionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetRevisionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
