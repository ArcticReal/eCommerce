package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceAdded;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.mapper.PayrollPreferenceMapper;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPayrollPreference extends Command {

private PayrollPreference elementToBeAdded;
public AddPayrollPreference(PayrollPreference elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PayrollPreference addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PayrollPreference", elementToBeAdded.mapAttributeField());
addedElement = PayrollPreferenceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PayrollPreferenceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
