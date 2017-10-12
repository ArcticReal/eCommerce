
package com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.event.ShipmentReceiptRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.mapper.ShipmentReceiptRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.model.ShipmentReceiptRole;


public class FindAllShipmentReceiptRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentReceiptRole> returnVal = new ArrayList<ShipmentReceiptRole>();
try{
List<GenericValue> results = delegator.findAll("ShipmentReceiptRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentReceiptRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentReceiptRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
