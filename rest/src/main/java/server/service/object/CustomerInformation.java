package server.service.object;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerInformation {

	private String name;
	private String userId;
	private String latitude;
	private String longitutde;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitutde() {
		return longitutde;
	}
	public void setLongitutde(String longitutde) {
		this.longitutde = longitutde;
	}
}
