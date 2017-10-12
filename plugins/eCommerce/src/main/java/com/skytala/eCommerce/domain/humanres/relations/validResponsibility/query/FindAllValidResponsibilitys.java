
package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event.ValidResponsibilityFound;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.mapper.ValidResponsibilityMapper;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;


public class FindAllValidResponsibilitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ValidResponsibility> returnVal = new ArrayList<ValidResponsibility>();
try{
List<GenericValue> results = delegator.findAll("ValidResponsibility", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ValidResponsibilityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ValidResponsibilityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
