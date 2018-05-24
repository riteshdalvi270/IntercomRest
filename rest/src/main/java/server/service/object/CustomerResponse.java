package server.service.object;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ritesh Dalvi
 **/
public class CustomerResponse {

	private String customerName;
	private String customerUserId;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerUserId() {
		return customerUserId;
	}
	public void setCustomerUserId(String customerUserId) {
		this.customerUserId = customerUserId;
	}

	
}
