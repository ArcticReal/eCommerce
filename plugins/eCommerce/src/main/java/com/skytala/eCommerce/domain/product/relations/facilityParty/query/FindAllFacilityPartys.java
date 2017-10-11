
package com.skytala.eCommerce.domain.product.relations.facilityParty.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.facilityParty.event.FacilityPartyFound;
import com.skytala.eCommerce.domain.product.relations.facilityParty.mapper.FacilityPartyMapper;
import com.skytala.eCommerce.domain.product.relations.facilityParty.model.FacilityParty;


public class FindAllFacilityPartys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FacilityParty> returnVal = new ArrayList<FacilityParty>();
try{
List<GenericValue> results = delegator.findAll("FacilityParty", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FacilityPartyMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FacilityPartyFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
