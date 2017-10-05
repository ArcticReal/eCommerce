
package com.skytala.eCommerce.domain.workEffortContentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workEffortContentType.event.WorkEffortContentTypeFound;
import com.skytala.eCommerce.domain.workEffortContentType.mapper.WorkEffortContentTypeMapper;
import com.skytala.eCommerce.domain.workEffortContentType.model.WorkEffortContentType;


public class FindAllWorkEffortContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortContentType> returnVal = new ArrayList<WorkEffortContentType>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
