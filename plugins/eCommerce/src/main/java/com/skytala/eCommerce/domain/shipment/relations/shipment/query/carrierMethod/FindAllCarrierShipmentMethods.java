
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.carrierMethod;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod.CarrierShipmentMethodFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierMethod.CarrierShipmentMethodMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierMethod.CarrierShipmentMethod;


public class FindAllCarrierShipmentMethods extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CarrierShipmentMethod> returnVal = new ArrayList<CarrierShipmentMethod>();
try{
List<GenericValue> results = delegator.findAll("CarrierShipmentMethod", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CarrierShipmentMethodMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CarrierShipmentMethodFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
