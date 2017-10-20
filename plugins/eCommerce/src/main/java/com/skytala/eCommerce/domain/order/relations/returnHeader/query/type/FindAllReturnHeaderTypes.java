
package com.skytala.eCommerce.domain.order.relations.returnHeader.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.type.ReturnHeaderTypeFound;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.type.ReturnHeaderTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.type.ReturnHeaderType;


public class FindAllReturnHeaderTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnHeaderType> returnVal = new ArrayList<ReturnHeaderType>();
try{
List<GenericValue> results = delegator.findAll("ReturnHeaderType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnHeaderTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnHeaderTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
