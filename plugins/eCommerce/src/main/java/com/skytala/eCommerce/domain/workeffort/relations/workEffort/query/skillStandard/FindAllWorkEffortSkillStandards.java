
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.skillStandard;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.skillStandard.WorkEffortSkillStandardFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.skillStandard.WorkEffortSkillStandardMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.skillStandard.WorkEffortSkillStandard;


public class FindAllWorkEffortSkillStandards extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortSkillStandard> returnVal = new ArrayList<WorkEffortSkillStandard>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortSkillStandard", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortSkillStandardMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortSkillStandardFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
