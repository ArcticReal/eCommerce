
package com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.event.CustRequestWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.mapper.CustRequestWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.model.CustRequestWorkEffort;


public class FindAllCustRequestWorkEfforts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestWorkEffort> returnVal = new ArrayList<CustRequestWorkEffort>();
try{
List<GenericValue> results = delegator.findAll("CustRequestWorkEffort", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestWorkEffortMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestWorkEffortFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
