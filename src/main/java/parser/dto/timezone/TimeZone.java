package parser.dto.timezone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeZone {
	private double dstOffset;
	private double rawOffset;
	private String status;
	private String timeZoneId;
	private String timeZoneName;

	@JsonCreator
	public TimeZone(@JsonProperty("dstOffset") double dstOffset,
			@JsonProperty("rawOffset") double rawOffset,
			@JsonProperty("status") String status,
			@JsonProperty("timeZoneId") String timeZoneId,
			@JsonProperty("Name") String timeZoneName) {
			super();
			this.dstOffset = dstOffset;
			this.rawOffset = rawOffset;
			this.status = status;
			this.timeZoneId = timeZoneId;
			this.timeZoneName = timeZoneName; 
	}

	public double getDstOffset() {
		return dstOffset;
	}

	public void setDstOffset(double dstOffset) {
		this.dstOffset = dstOffset;
	}

	public double getRawOffset() {
		return rawOffset;
	}

	public void setRawOffset(double rawOffset) {
		this.rawOffset = rawOffset;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dstOffset);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(rawOffset);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((timeZoneId == null) ? 0 : timeZoneId.hashCode());
		result = prime * result
				+ ((timeZoneName == null) ? 0 : timeZoneName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeZone other = (TimeZone) obj;
		if (Double.doubleToLongBits(dstOffset) != Double
				.doubleToLongBits(other.dstOffset))
			return false;
		if (Double.doubleToLongBits(rawOffset) != Double
				.doubleToLongBits(other.rawOffset))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (timeZoneId == null) {
			if (other.timeZoneId != null)
				return false;
		} else if (!timeZoneId.equals(other.timeZoneId))
			return false;
		if (timeZoneName == null) {
			if (other.timeZoneName != null)
				return false;
		} else if (!timeZoneName.equals(other.timeZoneName))
			return false;
		return true;
	}
	
}
