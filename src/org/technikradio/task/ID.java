package org.technikradio.task;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * This class is designed to be an exact ID for an object.<br/>
 * NOTE: if it is an weak fingerprint the is a (relatively)<br/>
 * hight change to produce a duplicated fingerprint
 * @author Doralitze
 */
public class ID {
	private final String baseString;
	private final String identifierString;
	private long runningIdetifier;
	
	/**
	 * This generates a new ID with an week fingerprint.
	 * Note that a week fingerprint is faster to generate but
	 * a strong fingerprint is harder to duplicate. This means
	 * that a week fingerprint may be more common than a strong
	 * one.
	 * @param base The name of the ID owner
	 */
	public ID(String base) {
		this(base, false);
	}
	
	/**
	 * This constructs a new ID
	 * @param base The name of the ID owner
	 * @param strong should the fingerprint be strong? (take longer)
	 */
	public ID(String base, boolean strong) {
		super();
		baseString = base;
		if(strong)
			identifierString = new BigInteger(130, new SecureRandom()).toString(32);
		else
			identifierString = new BigInteger(512, new SecureRandom()).toString(32);
	}
	
	/**
	 * This is the name value of the ID
	 * @return the name
	 */
	public String getName(){
		return baseString;
	}
	
	/**
	 * Checks if b1 is the same ID as b2
	 * @param b1 The first ID
	 * @param b2 The second ID
	 * @return true if b1 is the same ID as b2
	 */
	public static boolean identify(ID b1, ID b2){
		if(b1.getName() == b2.getName())
			if(b1.getFingerprint() == b2.getFingerprint())
				return true;
		return false;
	}
	
	/**
	 * This validates the ID of B1
	 * @param b1 The ID to check
	 * @param b2 The older ID
	 * @return A new ID for B1 if B2 already use the
	 * 			Fingerprint of B1
	 */
	public static ID validate(ID b1, ID b2){
		if((b1.getFingerprint() == b2.getFingerprint()) && !(ID.identify(b1, b2))){
			return new ID(b1.getName(), true);
		}
		return b1;
	}
	
	/**
	 * This returns the (hopefully) exact fingerprint
	 * @return the fingerprint
	 */
	public String getFingerprint(){
		return identifierString;
	}

	/**
	 * Returns an identifier to identify a running object
	 * @return the identifier
	 */
	public long getRunningIdentifier() {
		return runningIdetifier;
	}

	/**
	 * Set the identifier value
	 * @param runningIdentifier the value to set
	 */
	public void setRunningIdentifier(long runningIdentifier) {
		this.runningIdetifier = runningIdentifier;
	}

}
