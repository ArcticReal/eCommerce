
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.type.ShipmentTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.type.ShipmentTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.type.ShipmentType;


public class FindAllShipmentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentType> returnVal = new ArrayList<ShipmentType>();
try{
List<GenericValue> results = delegator.findAll("ShipmentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
