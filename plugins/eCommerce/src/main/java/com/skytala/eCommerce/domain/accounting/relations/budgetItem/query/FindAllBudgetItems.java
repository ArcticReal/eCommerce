
package com.skytala.eCommerce.domain.accounting.relations.budgetItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetItem.event.BudgetItemFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetItem.mapper.BudgetItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetItem.model.BudgetItem;


public class FindAllBudgetItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetItem> returnVal = new ArrayList<BudgetItem>();
try{
List<GenericValue> results = delegator.findAll("BudgetItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
