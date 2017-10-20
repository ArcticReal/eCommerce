
package com.skytala.eCommerce.domain.order.relations.requirement.query.budgetAllocation;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation.RequirementBudgetAllocationFound;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.budgetAllocation.RequirementBudgetAllocationMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.budgetAllocation.RequirementBudgetAllocation;


public class FindAllRequirementBudgetAllocations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementBudgetAllocation> returnVal = new ArrayList<RequirementBudgetAllocation>();
try{
List<GenericValue> results = delegator.findAll("RequirementBudgetAllocation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementBudgetAllocationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementBudgetAllocationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
