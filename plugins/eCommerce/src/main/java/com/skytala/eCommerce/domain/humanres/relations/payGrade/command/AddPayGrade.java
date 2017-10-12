package com.skytala.eCommerce.domain.humanres.relations.payGrade.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.event.PayGradeAdded;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.mapper.PayGradeMapper;
import com.skytala.eCommerce.domain.humanres.relations.payGrade.model.PayGrade;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPayGrade extends Command {

private PayGrade elementToBeAdded;
public AddPayGrade(PayGrade elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PayGrade addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPayGradeId(delegator.getNextSeqId("PayGrade"));
GenericValue newValue = delegator.makeValue("PayGrade", elementToBeAdded.mapAttributeField());
addedElement = PayGradeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PayGradeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
