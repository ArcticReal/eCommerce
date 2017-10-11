
package com.skytala.eCommerce.domain.product.relations.configOptionProductOption.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event.ConfigOptionProductOptionFound;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.mapper.ConfigOptionProductOptionMapper;
import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.model.ConfigOptionProductOption;


public class FindAllConfigOptionProductOptions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ConfigOptionProductOption> returnVal = new ArrayList<ConfigOptionProductOption>();
try{
List<GenericValue> results = delegator.findAll("ConfigOptionProductOption", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ConfigOptionProductOptionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ConfigOptionProductOptionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
