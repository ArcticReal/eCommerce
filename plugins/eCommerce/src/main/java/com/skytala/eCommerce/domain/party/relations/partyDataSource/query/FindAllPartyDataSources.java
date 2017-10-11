
package com.skytala.eCommerce.domain.party.relations.partyDataSource.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyDataSource.event.PartyDataSourceFound;
import com.skytala.eCommerce.domain.party.relations.partyDataSource.mapper.PartyDataSourceMapper;
import com.skytala.eCommerce.domain.party.relations.partyDataSource.model.PartyDataSource;


public class FindAllPartyDataSources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyDataSource> returnVal = new ArrayList<PartyDataSource>();
try{
List<GenericValue> results = delegator.findAll("PartyDataSource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyDataSourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyDataSourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
