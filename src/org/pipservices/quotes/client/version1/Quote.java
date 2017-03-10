package org.pipservices.quotes.client.version1;

import org.codehaus.jackson.annotate.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	private String _id;
	private MultiString _text;
	private MultiString _author;
	private String _status;
	private String[] _tags;
	private String[] _allTags;
	
	public Quote() {}

	@JsonProperty("id")
	public String getId() { return _id; }
	public void setId(String value) { _id = value; }
	
	@JsonProperty("text")
	public MultiString getText() { return _text; }
	public void setText(MultiString value) { _text = value; } 

	@JsonProperty("author")
	public MultiString getAuthor() { return _author; }
	public void setAuthor(MultiString value) { _author = value; }

	@JsonProperty("status")
	public String getStatus() { return _status; }
	public void setStatus(String value) { _status = value; }

	@JsonProperty("tags")
	public String[] getTags() { return _tags; }
	public void setTags(String[] value) { _tags = value; }

	@JsonProperty("all_tags")
	public String[] getAllTags() { return _allTags; }
	public void setAllTags(String[] value) { _allTags = value; }
}