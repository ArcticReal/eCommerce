
package com.skytala.eCommerce.domain.party.relations.addressMatchMap.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.event.AddressMatchMapFound;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.mapper.AddressMatchMapMapper;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;


public class FindAllAddressMatchMaps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AddressMatchMap> returnVal = new ArrayList<AddressMatchMap>();
try{
List<GenericValue> results = delegator.findAll("AddressMatchMap", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AddressMatchMapMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AddressMatchMapFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
