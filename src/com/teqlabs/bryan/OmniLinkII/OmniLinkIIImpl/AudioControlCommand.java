package com.teqlabs.bryan.OmniLinkII.OmniLinkIIImpl;

import net.homeip.mleclerc.omnilink.message.BaseZoneCommand;

@SuppressWarnings("serial")
public class AudioControlCommand extends BaseZoneCommand {

	public AudioControlCommand(int zone, int volume) {
		super(112, zone, volume);
	}

}
