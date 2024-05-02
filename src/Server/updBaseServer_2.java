package Server;
import Utils.CommandDTO;
import Utils.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.postgresql.Driver;

import java.io.*;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.ZonedDateTime;
import java.util.*;
import static Utils.CommandDTO.convertToCommand;
import org.postgresql.Driver;
public class updBaseServer_2
{
    private static volatile Vector vector = new Vector();

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1234);
//        socket.setSendBufferSize(50);
        byte[] receive = new byte[65536];

        DatagramPacket dpFromClient = null;

        byte[] receivedData = new byte[receive.length];

//        DatagramSocket receiverSocket = new DatagramSocket(1234);

        String filePath = args[0];

        //Driver
        if (filePath != null) {
            File file = new File(filePath);

            /**
             * Чтение файла.
             */
            try  (FileInputStream input = new FileInputStream(file);
                  InputStreamReader inputStreamReader = new InputStreamReader(input);
                  BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .serializeNulls()
                        .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                        .create();

                Type listType = new TypeToken<ArrayList<Vehicle>>() {}.getType();
                List<Vehicle> List = gson.fromJson(sb.toString(), listType);

                vector.addAll(List);

                Collections.sort(vector, Comparator.comparingInt(Vehicle::getEnginePower));
            } catch (IOException e) {
                System.out.println("Ошибка в обработке файла");
            } catch (NullPointerException ex) {
                System.out.println("Файл пуст(");
            }
        } else {
            System.out.println("Переменная окружения не установлена");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Save save = new Save(vector);
            save.execute(vector);
//            saveCollection();
            System.out.println("Сохраняем коллекцию");
        }));
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                String command = sc.nextLine();
                if (command.equals("save")) {
                    System.out.println("Сохраняем коллекцию");
                    Save save = new Save(vector);
                    save.execute(vector);
//                    saveCollection();
                }
            }
        }).start();

        Help help = new Help();
        Info info = new Info(vector);
        Show show = new Show(vector);
        Add add = new Add(vector);
        Update update = new Update(vector);
        Clear clear = new Clear(vector);
        Insert_at insert_at = new Insert_at(vector);
        Remove_by_id remove_by_id = new Remove_by_id(vector);
        Remove_at remove_at = new Remove_at(vector);
        Shuffle shuffle = new Shuffle(vector);
        Count_less_than_type count_less_than_type = new Count_less_than_type(vector);
        Filter_less_than_engine_power filter_less_than_engine_power = new Filter_less_than_engine_power(vector);
        Filter_contains_name filter_contains_name = new Filter_contains_name(vector);
        Execute_script execute_script = new Execute_script(
                help,
                info,
                show,
                add,
                update,
                remove_by_id,
                remove_at,
                clear,
                insert_at,
                shuffle,
                count_less_than_type,
                filter_contains_name,
                filter_less_than_engine_power,
                socket

        );

        while (true) {
//            dpFromClient = new DatagramPacket(receive, receive.length);
//            socket.receive(dpFromClient);

            int i = 0;
            while (true) {
                int chunkSize = 49;
                byte[] commandReceive = new byte[chunkSize+1];
                dpFromClient = new DatagramPacket(commandReceive, commandReceive.length);
                socket.receive(dpFromClient);

                byte[] chunkData = Arrays.copyOfRange(commandReceive, 1, commandReceive.length);
                System.arraycopy(chunkData, 0, receive, i*chunkSize, chunkData.length);
                i += 1;
                if (commandReceive[0] == 1) {
                    break;
                }
            }

            CommandDTO commandDTO = convertToCommand(receive);
            executeCommand(
                    commandDTO,
                    help,
                    info,
                    show,
                    add,
                    update,
                    remove_by_id,
                    remove_at,
                    clear,
                    insert_at,
                    shuffle,
                    count_less_than_type,
                    filter_contains_name,
                    filter_less_than_engine_power,
                    execute_script,
                    dpFromClient,
                    socket
            );

            receive = new byte[65536];
        }

    }


    public static void executeCommand(
            CommandDTO commandDTO,
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
            Command execute_script,
            DatagramPacket dpFromClient,
            DatagramSocket socket
    ) throws UnsupportedEncodingException {

        String text = null;
        if("help".equals(commandDTO.getName())) {
            text = help.execute(commandDTO.getPayload());
        }
        else if("info".equals(commandDTO.getName())) {
            text = info.execute(commandDTO.getPayload());
        }
        else if("show".equals(commandDTO.getName())) {
            text = show.execute(commandDTO.getPayload());
        }
        else if ("add".equals(commandDTO.getName())) {
            text = add.execute(commandDTO.getPayload());
        }
        else if ("update".equals(commandDTO.getName())) {
            text = update.execute(commandDTO.getPayload());
        }
        else if ("remove_by_id".equals(commandDTO.getName())) {
            text = remove_by_id.execute(commandDTO.getPayload());
        }
        else if ("remove_at".equals(commandDTO.getName())) {
            text = remove_at.execute(commandDTO.getPayload());
        }
        else if ("clear".equals(commandDTO.getName())) {
            text = clear.execute(commandDTO.getPayload());
        }
        else if ("insert_at".equals(commandDTO.getName())){
            text = insert_at.execute(commandDTO.getPayload());
        }
        else if ("shuffle".equals(commandDTO.getName())) {
            text = shuffle.execute(commandDTO.getPayload());
        }
        else if ("count_less_than_type".equals(commandDTO.getName())){
            text = count_less_than_type.execute(commandDTO.getPayload());
        }
        else if ("filter_contains_name".equals(commandDTO.getName())){
            text = filter_contains_name.execute(commandDTO.getPayload());
        }
        else if ("filter_less_than_engine_power".equals(commandDTO.getName())){
            text = filter_less_than_engine_power.execute(commandDTO.getPayload());
        }
        else if ("execute_script".equals(commandDTO.getName())){
            ((Execute_script) execute_script).setDpFromClient(dpFromClient);
            text = execute_script.execute(commandDTO.getPayload());
        }
        else {
            System.out.println("Не верно введена команда");
            text = "Не верно введена команда";
        }
        System.out.println("Выполняется:" + commandDTO.getName());
        byte[] buf = text.getBytes("UTF-8");
//        DatagramPacket DpSend =
//                new DatagramPacket(buf, buf.length, dpFromClient.getAddress(), dpFromClient.getPort());


//        try {
//            socket.send(DpSend);
//        } catch (IOException e) {
//            System.out.println("Не удалось выполнить операцию");
//        }
        int chunkSize = 49; // размер части файла
        int numChunks = (int) Math.ceil((double) buf.length / chunkSize);
        for (int i = 0; i < numChunks; i++) {
//            System.out.println(numChunks);
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
            DatagramPacket packet = new DatagramPacket(newChunkData, newChunkData.length, dpFromClient.getAddress(), dpFromClient.getPort());
            try {
            socket.send(packet);
            } catch (IOException e) {
            System.out.println("Не удалось выполнить операцию");
            }
        }
    }


}
// update 56789300
// remove_by_id 64984750
// remove_at 0
//[B@512ddf17
//[B@77556fd
//[B@368239c8