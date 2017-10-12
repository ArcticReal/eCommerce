
package com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.event.UnemploymentClaimFound;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.mapper.UnemploymentClaimMapper;
import com.skytala.eCommerce.domain.humanres.relations.unemploymentClaim.model.UnemploymentClaim;


public class FindAllUnemploymentClaims extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<UnemploymentClaim> returnVal = new ArrayList<UnemploymentClaim>();
try{
List<GenericValue> results = delegator.findAll("UnemploymentClaim", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(UnemploymentClaimMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new UnemploymentClaimFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
