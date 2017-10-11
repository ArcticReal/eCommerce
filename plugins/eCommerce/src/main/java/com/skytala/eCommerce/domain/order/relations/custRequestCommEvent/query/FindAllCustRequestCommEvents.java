
package com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.event.CustRequestCommEventFound;
import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.mapper.CustRequestCommEventMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.model.CustRequestCommEvent;


public class FindAllCustRequestCommEvents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestCommEvent> returnVal = new ArrayList<CustRequestCommEvent>();
try{
List<GenericValue> results = delegator.findAll("CustRequestCommEvent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestCommEventMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestCommEventFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
