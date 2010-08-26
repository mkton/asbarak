package org.ow2.frascati.demonstration.ase.audio;

import org.osoa.sca.annotations.Service;

@Service
public interface AlsamixerManager {
	
	/**
	 * Increase sound volume with alsamixer 
	 *  
	 * @param gain 
	 */
	public void increaseSoundLevel(int gain);
	
	/**
	 * Reduce sound volume with alsamixer
	 *  
	 * @param gain
	 */
	public void reduceSoundLevel(int gain);
		
	/**
	 * Get actual sound level
	 * @return sound level
	 */
	public int getSoundLevel();
	
}
