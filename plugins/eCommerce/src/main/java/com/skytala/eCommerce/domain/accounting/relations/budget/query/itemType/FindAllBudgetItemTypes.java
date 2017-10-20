
package com.skytala.eCommerce.domain.accounting.relations.budget.query.itemType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemType.BudgetItemTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.itemType.BudgetItemTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemType.BudgetItemType;


public class FindAllBudgetItemTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetItemType> returnVal = new ArrayList<BudgetItemType>();
try{
List<GenericValue> results = delegator.findAll("BudgetItemType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetItemTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetItemTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
