
package com.skytala.eCommerce.domain.returnReason.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.returnReason.event.ReturnReasonFound;
import com.skytala.eCommerce.domain.returnReason.mapper.ReturnReasonMapper;
import com.skytala.eCommerce.domain.returnReason.model.ReturnReason;


public class FindAllReturnReasons extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnReason> returnVal = new ArrayList<ReturnReason>();
try{
List<GenericValue> results = delegator.findAll("ReturnReason", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnReasonMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnReasonFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
