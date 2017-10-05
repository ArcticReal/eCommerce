package com.skytala.eCommerce.domain.surveyQuestionType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.surveyQuestionType.event.SurveyQuestionTypeUpdated;
import com.skytala.eCommerce.domain.surveyQuestionType.model.SurveyQuestionType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyQuestionType extends Command {

private SurveyQuestionType elementToBeUpdated;

public UpdateSurveyQuestionType(SurveyQuestionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyQuestionType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyQuestionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyQuestionType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyQuestionType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyQuestionType.class);
}
success = false;
}
Event resultingEvent = new SurveyQuestionTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
