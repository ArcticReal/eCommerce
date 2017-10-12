
package com.skytala.eCommerce.domain.humanres.relations.personTraining.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.event.PersonTrainingFound;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.mapper.PersonTrainingMapper;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;


public class FindAllPersonTrainings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PersonTraining> returnVal = new ArrayList<PersonTraining>();
try{
List<GenericValue> results = delegator.findAll("PersonTraining", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PersonTrainingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PersonTrainingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
