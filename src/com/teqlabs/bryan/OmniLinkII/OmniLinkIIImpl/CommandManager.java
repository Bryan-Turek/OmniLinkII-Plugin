package com.teqlabs.bryan.OmniLinkII.OmniLinkIIImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import net.homeip.mleclerc.omnilink.enumeration.Enum;
import net.homeip.mleclerc.omnilink.enumeration.EnumInfo;
import net.homeip.mleclerc.omnilink.message.SecurityCommand;
import net.homeip.mleclerc.omnilink.messagebase.RequestMessage;

public class CommandManager {
	
	@SuppressWarnings("rawtypes")
	private Map<String, Class> commandMap = new TreeMap<String, Class>();
	
	public CommandManager() {
		commandMap.put("audio.volume", AudioVolumeCommand.class);
		commandMap.put("audio.source", AudioSourceCommand.class);
		commandMap.put("audio.control", AudioControlCommand.class);
		commandMap.put("security", SecurityCommand.class);
	}

	@SuppressWarnings("rawtypes")
	/*
	 * This creates a request message from the omnilink.jar library.
	 * This function needs to be cleaned up.
	 */
	public RequestMessage createRequestMessage(String c) {
		
		StringTokenizer strtok = new StringTokenizer(c);
        String cmdName = strtok.nextToken();
        
        Class commandClass = commandMap.get(cmdName);
        List<String> argList = new ArrayList<String>();
        while(strtok.hasMoreTokens())
        {
            String token = strtok.nextToken();
            token = token.replace('_', ' ');
            argList.add(token);
        }
        
        Constructor[] constrs = commandClass.getConstructors();
        Constructor constr = null;
        for (int i = 0; i < constrs.length; i++)
        {
            if (constrs[i].getParameterTypes().length == argList.size())
            {
                constr = constrs[i];
                break;
            }
        }
        if (constr == null)
            return null;
        
        try {
	        Object args[] = new Object[argList.size()];
	        Class[] argTypes = constr.getParameterTypes();
	        Iterator iter = argList.iterator();
	        for (int i = 0; i < argTypes.length; i++)
	        {
	            String arg = (String) iter.next();
	            Class argType = argTypes[i];
	            if (argType == Integer.TYPE) {
	                args[i] = Integer.valueOf(arg.trim());
	            }
	            else if (argType == Double.TYPE)
	                args[i] = Double.valueOf(arg.trim());
	            else if (argType == String.class)
	                args[i] = arg;
	            else if (Enum.class.isAssignableFrom(argType))
	            {
	                Field field = argType.getField("metaInfo");
	                EnumInfo enumInfo = (EnumInfo) field.get(argType);
	                Enum enumeration = enumInfo.getByUserLabel(arg.trim());
	                args[i] = enumeration;
	            }
	            else if (argType == Boolean.TYPE) {
	            	args[i] = Boolean.valueOf(arg.trim());
	            }
	            else if (argType == Calendar.class) {
	            	long time = Long.parseLong(arg.trim());
	            	Calendar calendar = Calendar.getInstance();
	            	calendar.setTimeInMillis(time);
	            	args[i] = calendar;
	            }
	            else if (argType.isArray() && argType.getComponentType() == Integer.TYPE) {
	            	String[] strValues = arg.split(",");
	            	int[] values = new int[strValues.length];
	            	for (int j = 0; j < strValues.length; j++) {
	            		int intValue = Integer.valueOf(strValues[j]);
	            		values[j] = intValue;
	            	}
	            	args[i] = values;
	            }
	        }

	        return (RequestMessage) constr.newInstance(args);
	        
        } catch(Exception e) {
        	e.printStackTrace();
        }
		return null;
        
	}

}
