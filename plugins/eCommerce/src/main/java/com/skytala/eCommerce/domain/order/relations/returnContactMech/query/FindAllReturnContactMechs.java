
package com.skytala.eCommerce.domain.order.relations.returnContactMech.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechFound;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.mapper.ReturnContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;


public class FindAllReturnContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnContactMech> returnVal = new ArrayList<ReturnContactMech>();
try{
List<GenericValue> results = delegator.findAll("ReturnContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
