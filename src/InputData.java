import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputData {

public static void start(){
    String[] fullInfo;
    Person worker = new Person();
    boolean isExit = false;
    while(!isExit) {
        fullInfo = InputData.EnterTheData();

        worker = new Person(InputData.CreateName(fullInfo),
                InputData.CreateDate(fullInfo),
                InputData.CreateNumber(fullInfo),
                InputData.CreateSex(fullInfo));
        if(worker.birthday==null){
            System.out.println("Ошибка! В введенных данных нет дня рождения либо он указан некорректно. Повторите ввод");
            System.out.println();
        }
        else if(worker.number==-1){
            System.out.println("Ошибка! В введенных данных нет номера телефона либо он указан некорректно. Повторите ввод");
            System.out.println();
        }
        else if(worker.sex==null){
            System.out.println("Ошибка! В введенных данных нет указания пола либо он указан некорректно. Повторите ввод");
            System.out.println();
        }

        else{
            isExit=true;
        }
    }
    System.out.println(InputData.CollectString(worker.name, worker.birthday, worker.number, worker.sex));
    WorkingWithFile.CreateFileAndAdd(worker);
}

    public static String[] EnterTheData(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите следующие данные в произвольном порядке, разделяя их пробелом: " +
                "ФИО, дата рождения, номер телефона, пол\nФорматы данных:\n" +
                "фамилия, имя, отчество - строки\n" +
                "дата рождения - строка формата dd.mm.yyyy\n" +
                "номер телефона - целое беззнаковое число без форматирования\n" +
                "пол - символ латиницей f или m.\n");
        String[] dataArr = new String[0];
        boolean isExit = false;
        while(!isExit) {
        try{
            String data = bf.readLine();
            String delimiter = " ";
            dataArr = data.split(delimiter);
            if(dataArr.length<6){
                throw new RuntimeException("Ошибка! Вы ввели недостаточно информации. Повторите ввод");
            }
            else if(dataArr.length>6){
                throw new RuntimeException("Ошибка! Вы ввели слишком много информации. Повторите ввод");
            }
           else isExit = true;

        }
        catch (IOException ex) {

        }
        }

        return dataArr;
    }
    public static String CreateName(String[] dataArr) {
        String name = "";
        int count = 0;
        for(int i = 0; i<dataArr.length; i++){
            try {
                long b = Long.parseLong(dataArr[i]);
            }
            catch (NumberFormatException e) {
                if(!dataArr[i].equals("m")&& !dataArr[i].equals("f") && !dataArr[i].contains("."))
                {
                    if(count <2)  {name =name +  dataArr[i] + " "; count++;}
                    else name =name + dataArr[i] ;
                }
            }
        }
        return name;
    }
    public static String CreateDate(String[] dataArr) {

        String s =null;
        for (int i = 0; i < dataArr.length; i++) {

            if(dataArr[i].contains("."))
                if(dataArr[i].length()==10){
                    {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat();
                            format.applyPattern("dd.MM.yyyy");
                            Date docDate = format.parse(dataArr[i]);
                            s = dataArr[i];
                        } catch (Exception e) {
                        }
                    }
                }
        }
        return s;
    }
    public static long CreateNumber(String[] dataArr) {

        long b =-1;
        boolean isExit = false;
        while(!isExit) {
            for (int i = 0; i < dataArr.length; i++) {

                try {

                    long temp = Long.parseLong(dataArr[i]);
                    boolean isOnlyDigits = true;
                    for(int j = 0; j < dataArr[i].length() && isOnlyDigits; j++) {
                        if(!Character.isDigit(dataArr[i].charAt(j))) {
                            isOnlyDigits = false;
                        }
                        if(isOnlyDigits==true){
                            b=temp;
                            isExit = true;
                        }
                        else{
                            return b;

                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if(b==-1) {
                return b;
            }
        }
        return b;
    }
    public static String CreateSex(String[] dataArr){
        String sex = null;
        for(int i = 0; i<dataArr.length; i++){
                if(dataArr[i].equals("m")|| dataArr[i].equals("f"))
                {
                    sex = dataArr[i];
                }
            }
        return sex;
    }

    public static String CollectString(String name, String birthday, long number , String sex){
        String fullInfo = name+ " "+ birthday + " " + number + " " + sex;
        return fullInfo;
    }
}
