
package com.skytala.eCommerce.domain.order.relations.returnItemShipment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnItemShipment.event.ReturnItemShipmentFound;
import com.skytala.eCommerce.domain.order.relations.returnItemShipment.mapper.ReturnItemShipmentMapper;
import com.skytala.eCommerce.domain.order.relations.returnItemShipment.model.ReturnItemShipment;


public class FindAllReturnItemShipments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnItemShipment> returnVal = new ArrayList<ReturnItemShipment>();
try{
List<GenericValue> results = delegator.findAll("ReturnItemShipment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnItemShipmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnItemShipmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
