package org.pipservices.quotes.client.version1;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

// This is a temporary implementation
// We need to think how to make extensible multi-string class
// that can be serialized by standard JSON data serializer
public class MultiString extends HashMap<String, String> {
	private static final long serialVersionUID = -8647036935407360168L;

	public MultiString() {}
	
	private String getLanguage(String language) {
		if (this.get(language) != null)
			return this.get(language);
		if (this.get("en") != null)
			return this.get("en");
		if (this.size() > 0)
			return this.values().iterator().next();
		return null;
	}
	
	@JsonIgnore
	public String getEn() { return this.getLanguage("en"); }
	public void setEn(String value) { this.put("en", value); }

	@JsonIgnore
	public String getRu() { return this.getLanguage("ru"); }
	public void setRu(String value) { this.put("ru", value); }

	@JsonIgnore
	public String getSp() { return this.getLanguage("sp"); }
	public void setSp(String value) { this.put("sp", value); }

	@JsonIgnore
	public String getDe() { return this.getLanguage("de"); }
	public void setDe(String value) { this.put("de", value); }

	@JsonIgnore
	public String getPt() { return this.getLanguage("pt"); }
	public void setPt(String value) { this.put("pt", value); }

	@JsonIgnore
	public String getFr() { return this.getLanguage("fr"); }
	public void setFr(String value) { this.put("fr", value); }
}