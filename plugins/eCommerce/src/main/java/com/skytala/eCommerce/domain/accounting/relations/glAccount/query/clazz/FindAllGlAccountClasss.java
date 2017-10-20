
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.clazz;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.clazz.GlAccountClassFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.clazz.GlAccountClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.clazz.GlAccountClass;


public class FindAllGlAccountClasss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountClass> returnVal = new ArrayList<GlAccountClass>();
try{
List<GenericValue> results = delegator.findAll("GlAccountClass", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountClassMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountClassFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
