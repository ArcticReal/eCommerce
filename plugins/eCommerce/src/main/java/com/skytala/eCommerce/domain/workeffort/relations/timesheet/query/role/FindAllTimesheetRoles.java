
package com.skytala.eCommerce.domain.workeffort.relations.timesheet.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.event.role.TimesheetRoleFound;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.mapper.role.TimesheetRoleMapper;
import com.skytala.eCommerce.domain.workeffort.relations.timesheet.model.role.TimesheetRole;


public class FindAllTimesheetRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TimesheetRole> returnVal = new ArrayList<TimesheetRole>();
try{
List<GenericValue> results = delegator.findAll("TimesheetRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TimesheetRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TimesheetRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
