
package com.skytala.eCommerce.domain.party.relations.communicationEvent.query.product;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.event.product.CommunicationEventProductFound;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.product.CommunicationEventProductMapper;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.product.CommunicationEventProduct;


public class FindAllCommunicationEventProducts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommunicationEventProduct> returnVal = new ArrayList<CommunicationEventProduct>();
try{
List<GenericValue> results = delegator.findAll("CommunicationEventProduct", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommunicationEventProductMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommunicationEventProductFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
