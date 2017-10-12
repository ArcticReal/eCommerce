
package com.skytala.eCommerce.domain.shipment.relations.shipmentItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.event.ShipmentItemFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.mapper.ShipmentItemMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.model.ShipmentItem;


public class FindAllShipmentItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentItem> returnVal = new ArrayList<ShipmentItem>();
try{
List<GenericValue> results = delegator.findAll("ShipmentItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
