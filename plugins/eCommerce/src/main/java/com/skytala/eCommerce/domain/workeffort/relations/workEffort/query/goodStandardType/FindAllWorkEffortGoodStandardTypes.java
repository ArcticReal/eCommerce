
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.goodStandardType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType.WorkEffortGoodStandardTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.goodStandardType.WorkEffortGoodStandardTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandardType.WorkEffortGoodStandardType;


public class FindAllWorkEffortGoodStandardTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortGoodStandardType> returnVal = new ArrayList<WorkEffortGoodStandardType>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortGoodStandardType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortGoodStandardTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortGoodStandardTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
