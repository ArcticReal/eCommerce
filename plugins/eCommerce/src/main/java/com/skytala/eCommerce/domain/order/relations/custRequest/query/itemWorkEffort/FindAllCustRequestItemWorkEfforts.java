
package com.skytala.eCommerce.domain.order.relations.custRequest.query.itemWorkEffort;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort.CustRequestItemWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemWorkEffort.CustRequestItemWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemWorkEffort.CustRequestItemWorkEffort;


public class FindAllCustRequestItemWorkEfforts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestItemWorkEffort> returnVal = new ArrayList<CustRequestItemWorkEffort>();
try{
List<GenericValue> results = delegator.findAll("CustRequestItemWorkEffort", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestItemWorkEffortMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestItemWorkEffortFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
