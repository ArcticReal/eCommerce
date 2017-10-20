
package com.skytala.eCommerce.domain.humanres.relations.employment.query.appSourceType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.appSourceType.EmploymentAppSourceTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.appSourceType.EmploymentAppSourceTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.appSourceType.EmploymentAppSourceType;


public class FindAllEmploymentAppSourceTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmploymentAppSourceType> returnVal = new ArrayList<EmploymentAppSourceType>();
try{
List<GenericValue> results = delegator.findAll("EmploymentAppSourceType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmploymentAppSourceTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmploymentAppSourceTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
