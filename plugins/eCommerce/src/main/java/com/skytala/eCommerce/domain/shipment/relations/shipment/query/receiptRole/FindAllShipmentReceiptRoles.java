
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.receiptRole;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole.ShipmentReceiptRoleFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receiptRole.ShipmentReceiptRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receiptRole.ShipmentReceiptRole;


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
