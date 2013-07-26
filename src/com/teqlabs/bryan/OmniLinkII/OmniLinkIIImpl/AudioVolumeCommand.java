package com.teqlabs.bryan.OmniLinkII.OmniLinkIIImpl;

import net.homeip.mleclerc.omnilink.message.BaseZoneCommand;

@SuppressWarnings("serial")
public class AudioVolumeCommand extends BaseZoneCommand {

	public AudioVolumeCommand(int zone, int volume) {
		super(113, zone, volume);
	}

}
