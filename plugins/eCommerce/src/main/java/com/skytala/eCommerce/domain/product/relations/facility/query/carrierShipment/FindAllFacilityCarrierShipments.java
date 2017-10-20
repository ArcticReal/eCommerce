
package com.skytala.eCommerce.domain.product.relations.facility.query.carrierShipment;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment.FacilityCarrierShipmentFound;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.carrierShipment.FacilityCarrierShipmentMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment.FacilityCarrierShipment;


public class FindAllFacilityCarrierShipments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityCarrierShipment> returnVal = new ArrayList<FacilityCarrierShipment>();
try{
List<GenericValue> results = delegator.findAll("FacilityCarrierShipment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityCarrierShipmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityCarrierShipmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
