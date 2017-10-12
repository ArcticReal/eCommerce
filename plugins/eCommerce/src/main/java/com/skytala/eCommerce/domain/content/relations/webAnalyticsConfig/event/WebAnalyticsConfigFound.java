package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;
public class WebAnalyticsConfigFound implements Event{

	private List<WebAnalyticsConfig> webAnalyticsConfigs;

	public WebAnalyticsConfigFound(List<WebAnalyticsConfig> webAnalyticsConfigs) {
		this.webAnalyticsConfigs = webAnalyticsConfigs;
	}

	public List<WebAnalyticsConfig> getWebAnalyticsConfigs()	{
		return webAnalyticsConfigs;
	}

}
