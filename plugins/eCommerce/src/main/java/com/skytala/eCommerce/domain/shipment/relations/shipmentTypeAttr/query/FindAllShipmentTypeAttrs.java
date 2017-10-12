
package com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.event.ShipmentTypeAttrFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.mapper.ShipmentTypeAttrMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.model.ShipmentTypeAttr;


public class FindAllShipmentTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentTypeAttr> returnVal = new ArrayList<ShipmentTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("ShipmentTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
