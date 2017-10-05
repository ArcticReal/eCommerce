
package com.skytala.eCommerce.domain.mrpEventType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.mrpEventType.event.MrpEventTypeFound;
import com.skytala.eCommerce.domain.mrpEventType.mapper.MrpEventTypeMapper;
import com.skytala.eCommerce.domain.mrpEventType.model.MrpEventType;


public class FindAllMrpEventTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MrpEventType> returnVal = new ArrayList<MrpEventType>();
try{
List<GenericValue> results = delegator.findAll("MrpEventType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MrpEventTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MrpEventTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
