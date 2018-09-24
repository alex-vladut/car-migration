package ro.msg.car.entity;

public enum CarType {
	SPORT("Sport"),
	LIMOUSINE("Limousine"),
	SUV("SUV"),
	EPOCH("Epoch"),
	UNKNOWN("Unknown");
	
	private CarType(final String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	public static CarType getCarType(final String carType) {
		if(carType == null) {
			throw new NullPointerException("The argument should not be null!");
		}
		
		for(final CarType type : CarType.values()) {
			if(type.getValue().equals(carType)) {
				return type;
			}
		}
		
		return UNKNOWN;
	}
}
