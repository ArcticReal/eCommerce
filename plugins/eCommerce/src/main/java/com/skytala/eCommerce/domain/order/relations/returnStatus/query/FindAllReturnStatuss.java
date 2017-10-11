
package com.skytala.eCommerce.domain.order.relations.returnStatus.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnStatus.event.ReturnStatusFound;
import com.skytala.eCommerce.domain.order.relations.returnStatus.mapper.ReturnStatusMapper;
import com.skytala.eCommerce.domain.order.relations.returnStatus.model.ReturnStatus;


public class FindAllReturnStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnStatus> returnVal = new ArrayList<ReturnStatus>();
try{
List<GenericValue> results = delegator.findAll("ReturnStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
