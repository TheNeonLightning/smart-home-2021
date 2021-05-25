package ru.sbt.mipt.oop.RemoteControl;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {

    Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        Command command;
        if ((command = commandMap.get(buttonCode)) != null){
            command.execute();
        }
    }

    public void setCommand(String buttonCode, Command command) {
        if (ButtonCodes.contains(buttonCode)) {
            commandMap.put(buttonCode, command);
        }
    }
}
