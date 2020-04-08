import MyArrayDataException.MyArrayDataException;
import MySizeArrayException.MySizeArrayException;

public class Main {
    public static void main(String[] args) {
        String arr1[][] = {
                {"1", "2", "3", "4"},
                {"11", "21", "31","22"},
                {"12", "22", "32", "42"},
                {"13", "23", "33", "43"}
        };

        try {
            System.out.println(summArray(arr1));
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        } catch (MySizeArrayException e) {
            e.printStackTrace();
        }
    }

    public static int summArray(String array[][]) throws MyArrayDataException, MySizeArrayException {
        int sum = 0;
        if((array.length == 4)&&(array[0].length == 4)&&(array[1].length == 4)&&(array[2].length == 4)&&(array[3].length == 4)){
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length ; i++) {
                if(array[j][i].matches("\\d+")) {
                    sum += Integer.parseInt(array[j][i]);
                }else {
                    throw new MyArrayDataException("Невозможно преобразовать в цифру." + " Ячейка: [" + j + "][" + i + "], Значение: " + array[j][i]);
                }
            }
        }
        return sum;}
        else {
            throw new MySizeArrayException("Размер двумерного массива должен быть: [4][4]");
        }
    }
}
