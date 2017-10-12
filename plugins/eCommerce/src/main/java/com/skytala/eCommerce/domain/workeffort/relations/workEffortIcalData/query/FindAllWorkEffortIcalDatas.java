
package com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.event.WorkEffortIcalDataFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.mapper.WorkEffortIcalDataMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.model.WorkEffortIcalData;


public class FindAllWorkEffortIcalDatas extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortIcalData> returnVal = new ArrayList<WorkEffortIcalData>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortIcalData", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortIcalDataMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortIcalDataFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
