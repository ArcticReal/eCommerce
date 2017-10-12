
package com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.event.WorkEffortTypeAttrFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.mapper.WorkEffortTypeAttrMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTypeAttr.model.WorkEffortTypeAttr;


public class FindAllWorkEffortTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortTypeAttr> returnVal = new ArrayList<WorkEffortTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
