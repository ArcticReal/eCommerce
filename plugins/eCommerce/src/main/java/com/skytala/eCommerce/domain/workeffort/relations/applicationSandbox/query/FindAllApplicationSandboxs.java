
package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxFound;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.mapper.ApplicationSandboxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;


public class FindAllApplicationSandboxs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ApplicationSandbox> returnVal = new ArrayList<ApplicationSandbox>();
try{
List<GenericValue> results = delegator.findAll("ApplicationSandbox", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ApplicationSandboxMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ApplicationSandboxFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
