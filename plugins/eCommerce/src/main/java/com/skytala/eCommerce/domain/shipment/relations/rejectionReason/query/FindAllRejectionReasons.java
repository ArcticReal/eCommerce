
package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event.RejectionReasonFound;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.mapper.RejectionReasonMapper;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;


public class FindAllRejectionReasons extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RejectionReason> returnVal = new ArrayList<RejectionReason>();
try{
List<GenericValue> results = delegator.findAll("RejectionReason", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RejectionReasonMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RejectionReasonFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
