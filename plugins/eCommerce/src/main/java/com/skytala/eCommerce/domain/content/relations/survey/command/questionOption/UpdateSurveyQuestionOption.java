package com.skytala.eCommerce.domain.content.relations.survey.command.questionOption;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionOption.SurveyQuestionOptionUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyQuestionOption extends Command {

private SurveyQuestionOption elementToBeUpdated;

public UpdateSurveyQuestionOption(SurveyQuestionOption elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyQuestionOption getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyQuestionOption elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyQuestionOption", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyQuestionOption.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyQuestionOption.class);
}
success = false;
}
Event resultingEvent = new SurveyQuestionOptionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
