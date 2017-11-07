
package com.skytala.eCommerce.domain.login.relations.protectedView.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.login.relations.protectedView.event.ProtectedViewFound;
import com.skytala.eCommerce.domain.login.relations.protectedView.mapper.ProtectedViewMapper;
import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;


public class FindAllProtectedViews extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProtectedView> returnVal = new ArrayList<ProtectedView>();
try{
List<GenericValue> results = delegator.findAll("ProtectedView", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProtectedViewMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProtectedViewFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
