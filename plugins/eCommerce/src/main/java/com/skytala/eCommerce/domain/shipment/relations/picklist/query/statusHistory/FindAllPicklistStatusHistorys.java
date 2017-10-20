
package com.skytala.eCommerce.domain.shipment.relations.picklist.query.statusHistory;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory.PicklistStatusHistoryFound;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.statusHistory.PicklistStatusHistoryMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.statusHistory.PicklistStatusHistory;


public class FindAllPicklistStatusHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PicklistStatusHistory> returnVal = new ArrayList<PicklistStatusHistory>();
try{
List<GenericValue> results = delegator.findAll("PicklistStatusHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PicklistStatusHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PicklistStatusHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
