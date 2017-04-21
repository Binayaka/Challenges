package challenges.binayaka.visualzations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class StatCollector extends PApplet {

	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;
	InetAddress address;
	MulticastSocket clientSocket;
	List<String> names = new ArrayList<>();
	List<StatPendant> pendants = new ArrayList<>();

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		// Get the address that we are going to connect to
		try {
			address = InetAddress.getByName(INET_ADDR);
			clientSocket = new MulticastSocket(PORT);
			// Joint the Multicast group.
			clientSocket.joinGroup(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void draw() {
		background(0);
		byte[] buf = new byte[6400];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		try {
			clientSocket.receive(packet);
			ByteArrayInputStream baos = new ByteArrayInputStream(buf);
			ObjectInputStream oos = new ObjectInputStream(baos);
			Stat stat = (Stat) oos.readObject();
			StatPendant pendant = null;
			if (names.contains(stat.getComputerName())) {
				pendant = findCorrespondingPendant(stat.getComputerName());
				pendant.setStat(stat);
			} else {
				pendant = new StatPendant(this);
				pendant.setStat(stat);
				names.add(stat.getComputerName());
				pendants.add(pendant);
				System.out.println("Adding name:: " + stat.getComputerName());
			}
			System.out.println("Number of pendants:: " + pendants.size());
			for (StatPendant p : pendants) {
				p.update();
				p.show();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private StatPendant findCorrespondingPendant(String computerName) {
		for (StatPendant pendant : pendants) {
			if (computerName.equalsIgnoreCase(pendant.name)) {
				return pendant;
			}
		}
		return null;
	}

}
