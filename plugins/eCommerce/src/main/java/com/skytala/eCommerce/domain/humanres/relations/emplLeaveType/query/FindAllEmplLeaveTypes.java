
package com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.event.EmplLeaveTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.mapper.EmplLeaveTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplLeaveType.model.EmplLeaveType;


public class FindAllEmplLeaveTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplLeaveType> returnVal = new ArrayList<EmplLeaveType>();
try{
List<GenericValue> results = delegator.findAll("EmplLeaveType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplLeaveTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplLeaveTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
