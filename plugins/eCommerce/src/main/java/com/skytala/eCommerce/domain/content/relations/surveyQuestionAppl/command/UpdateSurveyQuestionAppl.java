package com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.event.SurveyQuestionApplUpdated;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.model.SurveyQuestionAppl;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyQuestionAppl extends Command {

private SurveyQuestionAppl elementToBeUpdated;

public UpdateSurveyQuestionAppl(SurveyQuestionAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyQuestionAppl getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyQuestionAppl elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyQuestionAppl", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyQuestionAppl.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyQuestionAppl.class);
}
success = false;
}
Event resultingEvent = new SurveyQuestionApplUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
