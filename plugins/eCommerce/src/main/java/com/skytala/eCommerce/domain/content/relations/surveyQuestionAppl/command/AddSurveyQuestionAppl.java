package com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.event.SurveyQuestionApplAdded;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.mapper.SurveyQuestionApplMapper;
import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.model.SurveyQuestionAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyQuestionAppl extends Command {

private SurveyQuestionAppl elementToBeAdded;
public AddSurveyQuestionAppl(SurveyQuestionAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyQuestionAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SurveyQuestionAppl", elementToBeAdded.mapAttributeField());
addedElement = SurveyQuestionApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyQuestionApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
