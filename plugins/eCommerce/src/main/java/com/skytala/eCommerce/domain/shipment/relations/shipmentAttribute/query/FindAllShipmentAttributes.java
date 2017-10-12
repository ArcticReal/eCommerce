
package com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.event.ShipmentAttributeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.mapper.ShipmentAttributeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.model.ShipmentAttribute;


public class FindAllShipmentAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentAttribute> returnVal = new ArrayList<ShipmentAttribute>();
try{
List<GenericValue> results = delegator.findAll("ShipmentAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
