
package com.skytala.eCommerce.domain.party.relations.telecomNumber.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.event.TelecomNumberFound;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.mapper.TelecomNumberMapper;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;


public class FindAllTelecomNumbers extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TelecomNumber> returnVal = new ArrayList<TelecomNumber>();
try{
List<GenericValue> results = delegator.findAll("TelecomNumber", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TelecomNumberMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TelecomNumberFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
