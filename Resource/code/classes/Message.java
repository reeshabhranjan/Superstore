package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    private String opcode;
    private ArrayList objects;

    public Message(String opcode, ArrayList objects) {
        this.opcode = opcode;
        this.objects = objects;
    }

    public Message() {
        opcode=null;
        objects=new ArrayList();
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public void setObjects(ArrayList objects) {
        this.objects = objects;
    }

    public String getOpcode() {
        return opcode;
    }

    public ArrayList getObjects() {
        return objects;
    }
}
