import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class WorkingWithFile {
    public static void CreateFileAndAdd(Person person) {
        String[] name = person.name.split(" ");
        String pathToFile=System.getProperty("user.dir") + "\\"+ name[0] + ".txt";

        try (FileWriter writer = new FileWriter(pathToFile, true)) {
            String text = InputData.CollectString(person.name, person.birthday, person.number, person.sex);
            writer.write(text);
            writer.append('\n');
            writer.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


