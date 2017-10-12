
package com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.event.ShipmentItemFeatureFound;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.mapper.ShipmentItemFeatureMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.model.ShipmentItemFeature;


public class FindAllShipmentItemFeatures extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShipmentItemFeature> returnVal = new ArrayList<ShipmentItemFeature>();
try{
List<GenericValue> results = delegator.findAll("ShipmentItemFeature", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShipmentItemFeatureMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShipmentItemFeatureFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
