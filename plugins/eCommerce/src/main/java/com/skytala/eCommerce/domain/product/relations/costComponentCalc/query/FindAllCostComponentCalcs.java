
package com.skytala.eCommerce.domain.product.relations.costComponentCalc.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.event.CostComponentCalcFound;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.mapper.CostComponentCalcMapper;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.model.CostComponentCalc;


public class FindAllCostComponentCalcs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CostComponentCalc> returnVal = new ArrayList<CostComponentCalc>();
try{
List<GenericValue> results = delegator.findAll("CostComponentCalc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CostComponentCalcMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CostComponentCalcFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
