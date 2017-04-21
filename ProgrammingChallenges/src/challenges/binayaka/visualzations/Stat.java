package challenges.binayaka.visualzations;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This is the data class that will be sent
 * 
 * @author Binayaka
 *
 */
public class Stat implements Serializable {
	/**
	 * The generated serial UID
	 */
	private static final long serialVersionUID = 8468621619270346780L;
	private String packetNumber;
	private String computerName;
	private String resourceUtil;

	public String getPacketNumber() {
		return packetNumber;
	}

	public void setPacketNumber(String packetNumber) {
		this.packetNumber = packetNumber;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getResourceUtil() {
		return resourceUtil;
	}

	public void setResourceUtil(String resourceUtil) {
		this.resourceUtil = resourceUtil;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}

	@Override
	public String toString() {
		return "Stat [packetNumber=" + packetNumber + ", computerName=" + computerName + ", resourceUtil="
				+ resourceUtil + ", getPacketNumber()=" + getPacketNumber() + ", getComputerName()=" + getComputerName()
				+ ", getResourceUtil()=" + getResourceUtil() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
		result = prime * result + ((packetNumber == null) ? 0 : packetNumber.hashCode());
		result = prime * result + ((resourceUtil == null) ? 0 : resourceUtil.hashCode());
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
		Stat other = (Stat) obj;
		if (computerName == null) {
			if (other.computerName != null)
				return false;
		} else if (!computerName.equals(other.computerName))
			return false;
		if (packetNumber == null) {
			if (other.packetNumber != null)
				return false;
		} else if (!packetNumber.equals(other.packetNumber))
			return false;
		if (resourceUtil == null) {
			if (other.resourceUtil != null)
				return false;
		} else if (!resourceUtil.equals(other.resourceUtil))
			return false;
		return true;
	}
}
