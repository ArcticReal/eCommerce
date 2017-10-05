package com.skytala.eCommerce.domain.surveyApplType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.surveyApplType.event.SurveyApplTypeAdded;
import com.skytala.eCommerce.domain.surveyApplType.mapper.SurveyApplTypeMapper;
import com.skytala.eCommerce.domain.surveyApplType.model.SurveyApplType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyApplType extends Command {

private SurveyApplType elementToBeAdded;
public AddSurveyApplType(SurveyApplType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyApplType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyApplTypeId(delegator.getNextSeqId("SurveyApplType"));
GenericValue newValue = delegator.makeValue("SurveyApplType", elementToBeAdded.mapAttributeField());
addedElement = SurveyApplTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyApplTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
