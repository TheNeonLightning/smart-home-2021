package ru.sbt.mipt.oop.RemoteControl;

public enum ButtonCodes {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    N1("1"),
    N2("2"),
    N3("3"),
    N4("4");

    private final String buttonCode;

    ButtonCodes(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public static boolean contains(String buttonCode) {

        for (ButtonCodes code : ButtonCodes.values()) {
            if (code.buttonCode.equals(buttonCode)) {
                return true;
            }
        }
        return false;
    }
}
