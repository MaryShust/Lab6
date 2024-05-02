package Server;

import Utils.CommandDTO;
import Utils.ExScPayload;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

/**
 * Команда Execute_script. Считывает и исполняет скрипт из указанного файла.
 * @author Mary
 */
public class Execute_script implements Command{


    private Command help;
    private Command info;
    private Command show;
    private Command add;
    private Command update;
    private Command remove_by_id;
    private Command remove_at;
    private Command clear;
    private Command insert_at;
    private Command shuffle;
    private Command count_less_than_type;
    private Command filter_contains_name;
    private Command filter_less_than_engine_power;
    private DatagramPacket dpFromClient;
    private DatagramSocket socket;

    Execute_script(
            Command help,
            Command info,
            Command show,
            Command add,
            Command update,
            Command remove_by_id,
            Command remove_at,
            Command clear,
            Command insert_at,
            Command shuffle,
            Command count_less_than_type,
            Command filter_contains_name,
            Command filter_less_than_engine_power,
            DatagramSocket socket
    ){
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.update = update;
        this.remove_by_id = remove_by_id;
        this.remove_at = remove_at;
        this.clear = clear;
        this.insert_at = insert_at;
        this.shuffle = shuffle;
        this.count_less_than_type = count_less_than_type;
        this.filter_contains_name = filter_contains_name;
        this.filter_less_than_engine_power = filter_less_than_engine_power;
        this.socket = socket;
    }

    public void setDpFromClient(DatagramPacket dpFromClient){
        this.dpFromClient = dpFromClient;
    }
    @Override
    public String execute(Object payload) {

        FileInputStream input = null;
        ExScPayload exScPayload = (ExScPayload) payload;
        String fileName = exScPayload.getAddress();
        HashSet<String> fileNames = exScPayload.getFileNames();
        System.out.println("execute fileName - " + fileName);

        if (fileNames.contains(fileName)) return "Может возникнуть бесконечная рекурсия";

        try {
            input = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            return ("Неверно задан файл");
        }

        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;

        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
                CommandDTO commandDTO;
                String[] arr = line.split(" ");
                String commandName = arr[0];
                if (arr.length == 1){
                    commandDTO = new CommandDTO(arr[0], null);
                }
                else {
                    if (commandName.equals("remove_at") || commandName.equals("remove_by_id") || commandName.equals("filter_less_than_engine_power")) {
                        commandDTO = new CommandDTO(commandName, Integer.parseInt(arr[1]));
                    }
                    else if (commandName.equals("execute_script")){
                        fileNames.add(fileName);
                        commandDTO = new CommandDTO(commandName, new ExScPayload(fileNames, arr[1]));
                    }
                    else {
                        commandDTO = new CommandDTO(commandName, arr[1]);
                    }
                }
                updBaseServer_2.executeCommand(commandDTO, help, info, show, add, update, remove_by_id, remove_at, clear, insert_at, shuffle, count_less_than_type, filter_contains_name, filter_less_than_engine_power, this, dpFromClient, socket);
            } catch (IOException e) {
                return ("Не возможно обработать команду");
            }
        }
        return "Ваша команда выполнена";
    }
}