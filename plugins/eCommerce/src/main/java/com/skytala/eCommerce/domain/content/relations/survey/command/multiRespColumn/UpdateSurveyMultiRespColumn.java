package com.skytala.eCommerce.domain.content.relations.survey.command.multiRespColumn;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn.SurveyMultiRespColumnUpdated;
import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSurveyMultiRespColumn extends Command {

private SurveyMultiRespColumn elementToBeUpdated;

public UpdateSurveyMultiRespColumn(SurveyMultiRespColumn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SurveyMultiRespColumn getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SurveyMultiRespColumn elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SurveyMultiRespColumn", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SurveyMultiRespColumn.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SurveyMultiRespColumn.class);
}
success = false;
}
Event resultingEvent = new SurveyMultiRespColumnUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
