package com.skytala.eCommerce.domain.surveyQuestionCategory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.surveyQuestionCategory.event.SurveyQuestionCategoryAdded;
import com.skytala.eCommerce.domain.surveyQuestionCategory.mapper.SurveyQuestionCategoryMapper;
import com.skytala.eCommerce.domain.surveyQuestionCategory.model.SurveyQuestionCategory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyQuestionCategory extends Command {

private SurveyQuestionCategory elementToBeAdded;
public AddSurveyQuestionCategory(SurveyQuestionCategory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyQuestionCategory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyQuestionCategoryId(delegator.getNextSeqId("SurveyQuestionCategory"));
GenericValue newValue = delegator.makeValue("SurveyQuestionCategory", elementToBeAdded.mapAttributeField());
addedElement = SurveyQuestionCategoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyQuestionCategoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
