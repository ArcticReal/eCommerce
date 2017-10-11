
package com.skytala.eCommerce.domain.order.relations.returnHeader.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderFound;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.ReturnHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;


public class FindAllReturnHeaders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnHeader> returnVal = new ArrayList<ReturnHeader>();
try{
List<GenericValue> results = delegator.findAll("ReturnHeader", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnHeaderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnHeaderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
