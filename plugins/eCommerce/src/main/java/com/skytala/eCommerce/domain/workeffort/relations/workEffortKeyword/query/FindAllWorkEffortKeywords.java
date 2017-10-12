
package com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.event.WorkEffortKeywordFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.mapper.WorkEffortKeywordMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.model.WorkEffortKeyword;


public class FindAllWorkEffortKeywords extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortKeyword> returnVal = new ArrayList<WorkEffortKeyword>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortKeyword", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortKeywordMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortKeywordFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
