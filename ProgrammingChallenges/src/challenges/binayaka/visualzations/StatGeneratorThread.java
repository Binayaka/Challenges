package challenges.binayaka.visualzations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * This will generate the actual stats.
 * 
 * @author Binayaka
 *
 */
public class StatGeneratorThread extends Thread {
	protected DatagramSocket socket = null;
	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;
	static InetAddress addr;
	static String name = "Unknown";
	static final OperatingSystemMXBean threadBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

	public StatGeneratorThread() throws IOException {
		this("StatGeneratorThread");
	}

	public StatGeneratorThread(String nameThread) {
		super(nameThread);
		name = System.getProperty("user.name");
		// Get the address that we are going to connect to.
		try {
			addr = InetAddress.getByName(INET_ADDR);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket();
			long packetNum = 0;
			while (true) {
				Stat stat = new Stat();
				packetNum++;
				stat.setPacketNumber(String.valueOf(packetNum));
				stat.setComputerName(name);
				Object value = null;
				for (Method method : threadBean.getClass().getDeclaredMethods()) {
					method.setAccessible(true);
					if (method.getName().equalsIgnoreCase("getSystemCpuLoad")
							&& Modifier.isPublic(method.getModifiers())) {
						try {
							value = method.invoke(threadBean);
						} catch (Exception e) {
							value = e;
						}
					}
				}
				stat.setResourceUtil(String.valueOf(value));
				final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
				final ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(stat);
				final byte[] data = baos.toByteArray();
				DatagramPacket msgPacket = new DatagramPacket(data, data.length, addr, PORT);
				serverSocket.send(msgPacket);
				System.out.println("Server sent packet with msg:: " + stat.toString());
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
