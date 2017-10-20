package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.note;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.note.MarketingCampaignNoteMapper;

public class MarketingCampaignNote implements Serializable{

private static final long serialVersionUID = 1L;
private String marketingCampaignId;
private String noteId;

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}


public Map<String, Object> mapAttributeField() {
return MarketingCampaignNoteMapper.map(this);
}
}
