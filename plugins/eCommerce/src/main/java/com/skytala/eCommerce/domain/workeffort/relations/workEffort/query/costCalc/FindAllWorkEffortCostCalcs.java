
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.costCalc;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.costCalc.WorkEffortCostCalcFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.costCalc.WorkEffortCostCalcMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.costCalc.WorkEffortCostCalc;


public class FindAllWorkEffortCostCalcs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortCostCalc> returnVal = new ArrayList<WorkEffortCostCalc>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortCostCalc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortCostCalcMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortCostCalcFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
