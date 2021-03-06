
package com.skytala.eCommerce.domain.order.relations.custRequest.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.type.CustRequestTypeFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.type.CustRequestTypeMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.type.CustRequestType;


public class FindAllCustRequestTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestType> returnVal = new ArrayList<CustRequestType>();
try{
List<GenericValue> results = delegator.findAll("CustRequestType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
