
package com.skytala.eCommerce.domain.content.relations.electronicText.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.electronicText.event.ElectronicTextFound;
import com.skytala.eCommerce.domain.content.relations.electronicText.mapper.ElectronicTextMapper;
import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;


public class FindAllElectronicTexts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ElectronicText> returnVal = new ArrayList<ElectronicText>();
try{
List<GenericValue> results = delegator.findAll("ElectronicText", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ElectronicTextMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ElectronicTextFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
