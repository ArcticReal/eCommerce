
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type.GlAccountTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.type.GlAccountTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;


public class FindAllGlAccountTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountType> returnVal = new ArrayList<GlAccountType>();
try{
List<GenericValue> results = delegator.findAll("GlAccountType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
