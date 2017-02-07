/*
  Processing Sound (c) 2013-2015 Wilm Thoben
  Part of the Processing project - http://processing.org

  Copyright (c) 2011-12 Ben Fry and Casey Reas

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
*/

package processing.sound;

import processing.core.PApplet;

public class Amplitude {

	PApplet parent;
	private Engine m_engine;

	public Engine getM_engine() {
		return m_engine;
	}

	private long ptr;

	public Amplitude(PApplet theParent) {
		this.parent = theParent;
		parent.registerMethod("dispose", this);
		Engine.setPreferences(theParent, 512, 44100);
		Engine.start();
	}

	public void input(SoundObject input) {
		ptr = Engine.amplitude(input.returnId());
	}

	public float analyze() {
		return Engine.poll_amplitude(ptr);
	}

	/*
	 * public void stop(){ m_engine.synthStop(m_nodeId); }
	 * 
	 * public int returnId(){ return m_nodeId; }
	 */
	public void dispose() {
		Engine.destroy_amplitude(ptr);
		// m_engine.synthStop(m_nodeId);
	}
}
