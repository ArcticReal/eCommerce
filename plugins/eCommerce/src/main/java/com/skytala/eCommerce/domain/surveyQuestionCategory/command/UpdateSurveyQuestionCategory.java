package com.skytala.eCommerce.domain.surveyQuestionCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.surveyQuestionCategory.event.SurveyQuestionCategoryUpdated;
import com.skytala.eCommerce.domain.surveyQuestionCategory.model.SurveyQuestionCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyQuestionCategory extends Command {

private SurveyQuestionCategory elementToBeUpdated;

public UpdateSurveyQuestionCategory(SurveyQuestionCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyQuestionCategory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyQuestionCategory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyQuestionCategory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyQuestionCategory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyQuestionCategory.class);
}
success = false;
}
Event resultingEvent = new SurveyQuestionCategoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
