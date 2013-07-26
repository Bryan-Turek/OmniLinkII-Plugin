package com.teqlabs.bryan.OmniLinkII.OmniLinkIIImpl;

import net.homeip.mleclerc.omnilink.message.BaseZoneCommand;

@SuppressWarnings("serial")
public class AudioSourceCommand extends BaseZoneCommand {

	public AudioSourceCommand(int zone, int volume) {
		super(114, zone, volume);
	}

}
