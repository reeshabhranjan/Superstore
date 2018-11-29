package classes;

import java.util.ArrayList;

public class Message {
    private int opcode;
    private ArrayList objects;

    public Message(int opcode, ArrayList objects) {
        this.opcode = opcode;
        this.objects = objects;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public void setObjects(ArrayList objects) {
        this.objects = objects;
    }

    public int getOpcode() {
        return opcode;
    }

    public ArrayList getObjects() {
        return objects;
    }
}
