
package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.event.WorkEffortAssocTypeAttrFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.mapper.WorkEffortAssocTypeAttrMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocTypeAttr.model.WorkEffortAssocTypeAttr;


public class FindAllWorkEffortAssocTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortAssocTypeAttr> returnVal = new ArrayList<WorkEffortAssocTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortAssocTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortAssocTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortAssocTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
