
package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event.BudgetItemTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.mapper.BudgetItemTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;


public class FindAllBudgetItemTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetItemTypeAttr> returnVal = new ArrayList<BudgetItemTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("BudgetItemTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetItemTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetItemTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
