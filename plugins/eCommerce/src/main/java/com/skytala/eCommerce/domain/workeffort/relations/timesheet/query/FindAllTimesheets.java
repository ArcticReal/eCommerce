
package com.skytala.eCommerce.domain.workeffort.relations.timesheet.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.TimesheetFound;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.mapper.TimesheetMapper;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.Timesheet;


public class FindAllTimesheets extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Timesheet> returnVal = new ArrayList<Timesheet>();
try{
List<GenericValue> results = delegator.findAll("Timesheet", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TimesheetMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TimesheetFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
