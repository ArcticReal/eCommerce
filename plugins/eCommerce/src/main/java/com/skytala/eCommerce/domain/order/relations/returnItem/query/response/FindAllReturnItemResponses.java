
package com.skytala.eCommerce.domain.order.relations.returnItem.query.response;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.response.ReturnItemResponseFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.response.ReturnItemResponseMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.response.ReturnItemResponse;


public class FindAllReturnItemResponses extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnItemResponse> returnVal = new ArrayList<ReturnItemResponse>();
try{
List<GenericValue> results = delegator.findAll("ReturnItemResponse", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnItemResponseMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnItemResponseFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
