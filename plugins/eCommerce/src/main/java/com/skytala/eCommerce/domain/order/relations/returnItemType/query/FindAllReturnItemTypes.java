
package com.skytala.eCommerce.domain.order.relations.returnItemType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnItemType.event.ReturnItemTypeFound;
import com.skytala.eCommerce.domain.order.relations.returnItemType.mapper.ReturnItemTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnItemType.model.ReturnItemType;


public class FindAllReturnItemTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnItemType> returnVal = new ArrayList<ReturnItemType>();
try{
List<GenericValue> results = delegator.findAll("ReturnItemType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnItemTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnItemTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
