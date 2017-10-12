
package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.event.WorkEffortGoodStandardFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.mapper.WorkEffortGoodStandardMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.model.WorkEffortGoodStandard;


public class FindAllWorkEffortGoodStandards extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortGoodStandard> returnVal = new ArrayList<WorkEffortGoodStandard>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortGoodStandard", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortGoodStandardMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortGoodStandardFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
