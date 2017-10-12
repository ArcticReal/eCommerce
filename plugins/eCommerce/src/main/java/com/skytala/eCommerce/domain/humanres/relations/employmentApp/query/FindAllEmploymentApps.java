
package com.skytala.eCommerce.domain.humanres.relations.employmentApp.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.employmentApp.event.EmploymentAppFound;
import com.skytala.eCommerce.domain.humanres.relations.employmentApp.mapper.EmploymentAppMapper;
import com.skytala.eCommerce.domain.humanres.relations.employmentApp.model.EmploymentApp;


public class FindAllEmploymentApps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmploymentApp> returnVal = new ArrayList<EmploymentApp>();
try{
List<GenericValue> results = delegator.findAll("EmploymentApp", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmploymentAppMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmploymentAppFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
