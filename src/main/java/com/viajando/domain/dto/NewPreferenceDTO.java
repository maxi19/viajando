package com.viajando.domain.dto;
import java.io.Serializable;
import java.util.List;



public class NewPreferenceDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String accessToken;
    private List<PreferenceItem> items;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public List<PreferenceItem> getItems() {
		return items;
	}
	public void setItems(List<PreferenceItem> items) {
		this.items = items;
	}

    
}
