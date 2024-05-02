package Utils;

import java.io.Serializable;
import java.util.HashSet;

public class ExScPayload implements Serializable {
    private HashSet<String> fileNames;
    private String address;

    public ExScPayload(HashSet<String> fileNames, String adress){
        this.fileNames = fileNames;
        this.address = adress;
    }

    public HashSet<String> getFileNames(){
        return fileNames;
    }

    public String getAddress(){
        return address;
    }
}
