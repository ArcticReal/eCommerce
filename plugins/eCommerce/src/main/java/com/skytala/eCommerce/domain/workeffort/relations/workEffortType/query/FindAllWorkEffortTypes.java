
package com.skytala.eCommerce.domain.workeffort.relations.workEffortType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.event.WorkEffortTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.mapper.WorkEffortTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortType.model.WorkEffortType;


public class FindAllWorkEffortTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortType> returnVal = new ArrayList<WorkEffortType>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
