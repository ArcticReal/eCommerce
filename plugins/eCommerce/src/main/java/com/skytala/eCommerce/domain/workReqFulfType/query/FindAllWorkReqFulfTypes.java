
package com.skytala.eCommerce.domain.workReqFulfType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workReqFulfType.event.WorkReqFulfTypeFound;
import com.skytala.eCommerce.domain.workReqFulfType.mapper.WorkReqFulfTypeMapper;
import com.skytala.eCommerce.domain.workReqFulfType.model.WorkReqFulfType;


public class FindAllWorkReqFulfTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkReqFulfType> returnVal = new ArrayList<WorkReqFulfType>();
try{
List<GenericValue> results = delegator.findAll("WorkReqFulfType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkReqFulfTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkReqFulfTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
