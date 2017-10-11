
package com.skytala.eCommerce.domain.order.relations.custRequest.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.CustRequestFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.CustRequestMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.CustRequest;


public class FindAllCustRequests extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequest> returnVal = new ArrayList<CustRequest>();
try{
List<GenericValue> results = delegator.findAll("CustRequest", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
