package Client;
import Utils.CommandDTO;
import Utils.ExScPayload;
import Utils.VehicleAt;
import Utils.VehicleUpd;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import static Utils.CommandDTO.convertFromCommand;
import static Utils.CommandDTO.convertToCommand;

public class udpBaseClient_2
{
    public static <User> void main(String args[]) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);


        DatagramSocket socket = new DatagramSocket();
//        socket.setSendBufferSize(50);

        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        Thread tr = new Thread(() -> {
            try {

                while (true) {
                    byte[] buffer = new byte[65536];
//                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//                    socket.receive(packet);
                    int i = 0;
                    while (true) {
                        int chunkSize = 49;
                        byte[] commandReceive = new byte[chunkSize+1];
                        DatagramPacket dpFromServer = new DatagramPacket(commandReceive, commandReceive.length);
                        socket.receive(dpFromServer);

                        byte[] chunkData = Arrays.copyOfRange(commandReceive, 1, commandReceive.length);
                        System.arraycopy(chunkData, 0, buffer, i*chunkSize, chunkData.length);
                        i += 1;
                        if (commandReceive[0] == 1) {
                            break;
                        }
                    }
                    String strr = new String(buffer, "UTF-8");
                    System.out.println("Server:- " + strr);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        tr.start();

        CommandDTO commandDTO = null;
        while (true)
        {
            String variableValue = scanner.nextLine().trim();
            String[] listSplit = variableValue.split(" ");
            String variableValueResult = listSplit[0].trim().toLowerCase();
            for (int index = 1; index < listSplit.length; index++) {
                variableValueResult = variableValueResult + " " + listSplit[index].trim();
            }
            For fa = new For(variableValueResult);
            if("exit".equals(variableValueResult)) {
                System.exit(130);
                break;
            }
            if("help".equals(variableValueResult)) {
                commandDTO = new CommandDTO("help", null);
            }
            else if("info".equals(variableValueResult)) {
                commandDTO = new CommandDTO("info", null);
            }
            else if("show".equals(variableValueResult)) {
                commandDTO = new CommandDTO("show", null);
            }
            else if (variableValueResult.startsWith("add")) {
                commandDTO = new CommandDTO("add", fa.foradd());
            }
            else if (variableValueResult.startsWith("update")) {
                String[] parts = variableValueResult.split(" ");
                try {
                    if (parts.length == 2) {
                        long id = Long.parseLong(parts[1]);
                        commandDTO = new CommandDTO("update", new VehicleUpd(id, fa.foradd()));
                    } else {
                        System.out.println("Неверный формат команды");
                    }
                } catch (Exception e) {
                    System.out.println("Неверный формат команды");
                }

            }
            else if (variableValueResult.startsWith("remove_by_id")) {
                String[] parts = variableValueResult.split(" ");
                try {
                    if (parts.length == 2) {
                        long id = Long.parseLong(parts[1]);
                        commandDTO = new CommandDTO("remove_by_id", id);
                    } else {
                        System.out.println("Неверный формат команды");
                    }
                } catch (Exception e) {
                    System.out.println("Неверный формат команды");
                }

            }
            else if (variableValueResult.startsWith("remove_at")) {
                String[] parts = variableValueResult.split(" ");
                try {
                    if (parts.length == 2) {
                        int index = Integer.parseInt(parts[1]);
                        commandDTO = new CommandDTO("remove_at", index);
                    } else {
                        System.out.println("Неверный формат команды");
                    }
                } catch (Exception e) {
                    System.out.println("Неверный формат команды");
                }

            }
            else if ("clear".equals(variableValueResult)) {
                commandDTO = new CommandDTO("clear", null);
            }
            else if (variableValueResult.startsWith("insert_at")){
                String[] parts = variableValueResult.split(" ");
                try {
                    if (parts.length == 2) {
                        int index1 = Integer.parseInt(parts[1]);
                        commandDTO = new CommandDTO("insert_at", new VehicleAt(index1, fa.foradd()));
                    } else {
                        System.out.println("Неверный формат команды");
                    }
                } catch (Exception e) {
                    System.out.println("Неверный формат команды");
                }

            }
            else if ("shuffle".equals(variableValueResult)) {
                commandDTO = new CommandDTO("shuffle", null);
            }
            else if (variableValueResult.startsWith("count_less_than_type")){
                String[] parts = variableValueResult.split(" ");
                if (parts.length == 2) {
                    try {
                        commandDTO = new CommandDTO("count_less_than_type", parts[1]);
                    } catch (Exception ex1) {
                        System.out.println("Должен быть введен элемент из enum FuelType");
                    }

                } else {
                    System.out.println("Неверный формат команды");
                }
            }
            else if (variableValueResult.startsWith("filter_contains_name")){
                String[] parts = variableValueResult.split(" ");
                if (parts.length == 2) {
                    commandDTO = new CommandDTO("filter_contains_name", parts[1]);
                } else {
                    System.out.println("Неверный формат команды");
                }
            }
            else if (variableValueResult.startsWith("filter_less_than_engine_power")){
                String[] parts = variableValueResult.split(" ");
                if (parts.length == 2) {
                    try {
                        int enginePower = Integer.parseInt(parts[1]);
                        commandDTO = new CommandDTO("filter_less_than_engine_power", enginePower);
                    } catch (NumberFormatException ex) {
                        System.out.println("Должно быть введено целочисленное число");
                    }
                } else {
                    System.out.println("Неверный формат команды");
                }
            }
            else if (variableValueResult.startsWith("execute_script")){
                String[] parts = variableValueResult.split(" ");
                if (parts.length == 2) {
                    try {
                        commandDTO = new CommandDTO("execute_script", new ExScPayload(new HashSet<String>(),parts[1]));
                    } catch (NumberFormatException ex) {
                        System.out.println("Неверный формат файла");
                    }
                } else {
                    System.out.println("Неверный формат команды");
                }
            }
            else {
                System.out.println("Введена неверная команда");
            }

            try {
                System.out.println(commandDTO.getName());
                if (commandDTO.getName() != null){
                    buf = convertFromCommand(commandDTO);

//                    System.out.println(buf.length);
                    int chunkSize = 49; // размер части файла
                    int numChunks = (int) Math.ceil((double) buf.length / chunkSize);
                    for (int i = 0; i < numChunks; i++) {
//                        System.out.println(numChunks);

                        int start = i * chunkSize;
                        int endd = Math.min(start + chunkSize, buf.length);
                        byte[] chunkData = Arrays.copyOfRange(buf, start, endd);
                        byte[] newChunkData = new byte[chunkData.length + 1];
                        System.arraycopy(chunkData, 0, newChunkData, 1, chunkData.length);
                        if (i+1 == numChunks) {
                            newChunkData[0] = 1;
                        } else {
                            newChunkData[0] = 0;
                        }
                        DatagramPacket packet = new DatagramPacket(newChunkData, newChunkData.length, ip, 1234);
                        socket.send(packet);
                        commandDTO = null;
                    }


//                    DatagramPacket DpSend =
//                            new DatagramPacket(buf, buf.length, ip, 1234);
//                    socket.send(DpSend);
                }
            } catch (Exception e){
                System.out.println("Не верный формат ввода");
            }

        }
    }
}