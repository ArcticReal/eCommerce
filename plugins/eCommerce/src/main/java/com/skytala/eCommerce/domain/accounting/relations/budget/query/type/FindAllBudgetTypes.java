
package com.skytala.eCommerce.domain.accounting.relations.budget.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.type.BudgetTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.type.BudgetTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.type.BudgetType;


public class FindAllBudgetTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetType> returnVal = new ArrayList<BudgetType>();
try{
List<GenericValue> results = delegator.findAll("BudgetType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
