package challenges.binayaka.visualzations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import challenges.binayaka.common.Drawable;
import processing.core.PVector;

public class StatPendant implements Drawable {
	Stat currentStat = null;
	BigDecimal utilVal = null;
	String name = "Unknown";
	PVector pos = null;
	StatCollector main;

	public StatPendant(StatCollector _main) {
		main = _main;
		calculatePos(_main);
	}

	private void calculatePos(StatCollector _main) {
		pos = new PVector(150, 150);
		if (!main.pendants.isEmpty()) {
			StatPendant lastOne = main.pendants.get(main.pendants.size() - 1);
			if (lastOne.pos.x + 150 > main.width) {
				pos = new PVector(150, lastOne.pos.y + 150);
			} else {
				pos = new PVector(lastOne.pos.x + 150, lastOne.pos.y);
			}
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentStat == null) ? 0 : currentStat.getComputerName().hashCode());
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
		StatPendant other = (StatPendant) obj;
		if (currentStat == null) {
			if (other.currentStat != null)
				return false;
		} else if (!currentStat.getComputerName().equals(other.currentStat.getComputerName()))
			return false;
		return true;
	}

	public void setStat(Stat s) {
		currentStat = s;
	}

	@Override
	public void show() {
		main.pushMatrix();
		main.translate(this.pos.x, this.pos.y);
		main.noFill();
		main.text(name, -10, 0);
		main.text(utilVal.toPlainString(), 0, 10);
		main.stroke(255);
		main.ellipse(0, 0, 100, 100);
		main.popMatrix();
	}

	@Override
	public void update() {
		utilVal = new BigDecimal((Double.parseDouble(currentStat.getResourceUtil()) * 100)).setScale(2,
				RoundingMode.HALF_EVEN);
		name = currentStat.getComputerName();
	}

}
