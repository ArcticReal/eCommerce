
package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.event.ShipmentPackageContentFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.mapper.ShipmentPackageContentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.model.ShipmentPackageContent;


public class FindAllShipmentPackageContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentPackageContent> returnVal = new ArrayList<ShipmentPackageContent>();
try{
List<GenericValue> results = delegator.findAll("ShipmentPackageContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentPackageContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentPackageContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
