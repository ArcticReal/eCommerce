package com.skytala.eCommerce.domain.humanres.relations.employment.command.app;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.employment.event.app.EmploymentAppAdded;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.app.EmploymentAppMapper;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.app.EmploymentApp;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmploymentApp extends Command {

private EmploymentApp elementToBeAdded;
public AddEmploymentApp(EmploymentApp elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmploymentApp addedElement = null;
boolean success = false;
try {
elementToBeAdded.setApplicationId(delegator.getNextSeqId("EmploymentApp"));
GenericValue newValue = delegator.makeValue("EmploymentApp", elementToBeAdded.mapAttributeField());
addedElement = EmploymentAppMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmploymentAppAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
