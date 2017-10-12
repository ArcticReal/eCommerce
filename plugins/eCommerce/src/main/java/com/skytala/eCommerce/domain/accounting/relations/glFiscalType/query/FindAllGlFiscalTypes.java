
package com.skytala.eCommerce.domain.accounting.relations.glFiscalType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.event.GlFiscalTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.mapper.GlFiscalTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.glFiscalType.model.GlFiscalType;


public class FindAllGlFiscalTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlFiscalType> returnVal = new ArrayList<GlFiscalType>();
try{
List<GenericValue> results = delegator.findAll("GlFiscalType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlFiscalTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlFiscalTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
