
package com.skytala.eCommerce.domain.humanres.relations.employment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.EmploymentFound;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.EmploymentMapper;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;


public class FindAllEmployments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Employment> returnVal = new ArrayList<Employment>();
try{
List<GenericValue> results = delegator.findAll("Employment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmploymentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmploymentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
