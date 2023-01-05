import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Zimmer
 * last modified 05.01.2023
 * 
 * Abstract DeviceHandler class using a createDevice() factory method.
 * Which kind of devices we're managing is decided at runtime by implementations of our framework classes DeviceHandler and Device.
 */
public abstract class DeviceHandler {
	
	private final List<Device> deviceList = new ArrayList<>();
	
	/*
	 * Invokes the abstract factory method to create the new device list entry.
	 */
	public void registerNewDevice(String deviceId) {
		deviceList.add(createDevice(deviceId));
	}
	
	/*
	 * Our factory method.
	 */
	abstract protected Device createDevice(String deviceId);

	public List<Device> getList() {
		return this.deviceList;
	}
}
