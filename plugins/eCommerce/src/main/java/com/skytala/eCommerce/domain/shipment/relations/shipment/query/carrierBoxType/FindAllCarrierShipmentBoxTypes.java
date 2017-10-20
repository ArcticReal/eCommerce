
package com.skytala.eCommerce.domain.shipment.relations.shipment.query.carrierBoxType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType.CarrierShipmentBoxTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierBoxType.CarrierShipmentBoxTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierBoxType.CarrierShipmentBoxType;


public class FindAllCarrierShipmentBoxTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CarrierShipmentBoxType> returnVal = new ArrayList<CarrierShipmentBoxType>();
try{
List<GenericValue> results = delegator.findAll("CarrierShipmentBoxType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CarrierShipmentBoxTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CarrierShipmentBoxTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
