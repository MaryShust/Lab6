package Utils;
import java.io.*;

public class CommandDTO implements Serializable {
    private String name;
    private Object payload;

    public CommandDTO(String name, Object payload){
        this.name = name;
        this.payload = payload;
    }

    public String getName() {
        return name;
    }

    public Object getPayload(){
        return payload;
    }



    public static byte[] convertFromCommand(CommandDTO commandDTO) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(commandDTO);
            out.close();
//            out.flush();
            return bos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static CommandDTO convertToCommand(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        try (ObjectInput in = new ObjectInputStream(bis)) {
            CommandDTO comm = (CommandDTO) in.readObject();
            in.close();
            return comm;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    @Override
    public String toString() {
        String info = "";
        info += name;
        info += payload;
        return info;
    }
}

