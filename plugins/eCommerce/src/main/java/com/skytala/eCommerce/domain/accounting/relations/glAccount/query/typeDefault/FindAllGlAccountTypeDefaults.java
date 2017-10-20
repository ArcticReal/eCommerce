
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.typeDefault;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.typeDefault.GlAccountTypeDefaultMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault.GlAccountTypeDefault;


public class FindAllGlAccountTypeDefaults extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountTypeDefault> returnVal = new ArrayList<GlAccountTypeDefault>();
try{
List<GenericValue> results = delegator.findAll("GlAccountTypeDefault", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountTypeDefaultMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountTypeDefaultFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
