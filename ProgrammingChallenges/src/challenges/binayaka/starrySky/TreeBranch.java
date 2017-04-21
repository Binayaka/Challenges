package challenges.binayaka.starrySky;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

public class TreeBranch implements Drawable {

	Stage main;
	PVector begin;
	PVector end;
	TreeBranch parent;
	boolean finished = false;

	public TreeBranch(Stage _main, PVector _begin, PVector _end, TreeBranch _parent) {
		main = _main;
		begin = _begin;
		end = _end;
		parent = _parent;
	}

	@Override
	public void show() {
		main.stroke(255);
		main.line(this.begin.x, this.begin.y, this.end.x, this.end.y);
	}

	@Override
	public void update() {
	}

	public TreeBranch branchA() {
		PVector dir = PVector.sub(this.end, this.begin);
		dir.rotate(PApplet.PI / 6);
		dir.mult(0.67f);
		PVector newEnd = PVector.add(this.end, dir);
		TreeBranch b = new TreeBranch(main, this.end, newEnd, this);
		main.addNewTraversals(new TreeBranchTraversalCircle(main, b, this));
		return b;
	}

	public TreeBranch branchB() {
		PVector dir = PVector.sub(this.end, this.begin);
		dir.rotate(-PApplet.PI / 4);
		dir.mult(0.67f);
		PVector newEnd = PVector.add(this.end, dir);
		TreeBranch b = new TreeBranch(main, this.end, newEnd, this);
		main.addNewTraversals(new TreeBranchTraversalCircle(main, b, this));
		return b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + (finished ? 1231 : 1237);
		result = prime * result + ((main == null) ? 0 : main.hashCode());
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
		TreeBranch other = (TreeBranch) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (finished != other.finished)
			return false;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		return true;
	}
}
