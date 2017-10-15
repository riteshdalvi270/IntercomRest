package intercom.buildrelationship.object.criteria.offices;

public class CustomerInformation {

	private final String name;
	private final String userId;
	private final String latitude;
	private final String longitutde;
	
	public String getName() {
		return name;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public String getLongitude() {
		return longitutde;
	}
	
	private CustomerInformation(final Builder builder) {
		this.name = builder.name;
		this.userId = builder.userId;
		this.latitude= builder.latitude;
		this.longitutde = builder.longitutde;
	}
	
	public static class Builder {
		private String name;
		private String userId;
		private String latitude;
		private String longitutde;
		
		public static Builder create() {
			return new Builder();
		}
		
		private Builder() {
			
		}
		
		public Builder withName(final String name) {
			this.name = name;
			return this;
		}
		
		public Builder withUserId(final String userId) {
			this.userId = userId;
			return this;
		}
		
		public Builder withLatitude(final String latitude) {
			this.latitude = latitude;
			return this;
		}
		
		public Builder withlongitude(final String longitude) {
			this.longitutde = longitude;
			return this;
		}
		
		public CustomerInformation build() {
			return new CustomerInformation(this);
		}
	}
}
