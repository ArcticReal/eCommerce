
package com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.event.WorkEffortContentFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.mapper.WorkEffortContentMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.model.WorkEffortContent;


public class FindAllWorkEffortContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortContent> returnVal = new ArrayList<WorkEffortContent>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
