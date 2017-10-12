
package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.MrpEventFound;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.mapper.MrpEventMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;


public class FindAllMrpEvents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MrpEvent> returnVal = new ArrayList<MrpEvent>();
try{
List<GenericValue> results = delegator.findAll("MrpEvent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MrpEventMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MrpEventFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
