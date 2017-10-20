
package com.skytala.eCommerce.domain.party.relations.party.query.geoPoint;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.geoPoint.PartyGeoPointFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.geoPoint.PartyGeoPointMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.geoPoint.PartyGeoPoint;


public class FindAllPartyGeoPoints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyGeoPoint> returnVal = new ArrayList<PartyGeoPoint>();
try{
List<GenericValue> results = delegator.findAll("PartyGeoPoint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyGeoPointMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyGeoPointFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
