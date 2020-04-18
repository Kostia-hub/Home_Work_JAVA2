/**
 * Java2. Homework 5
 * Created by СПБ on 18.04.2020.
 *
 * @author Fedorov Konstantin
 *
 */

public class ThreadsClass {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        ThreadsClass obj = new ThreadsClass();

        System.out.println("Время на пересчет массива целиком: " + obj.countAll(arr));
        System.out.println("Время на пересчет массива, разделенного на две половины, двумя потоками: " + obj.countInHalfbyThread(arr));
        System.out.println("Время на пересчет массива, разделенного на две половины, одним потоком: " + obj.countInHalf(arr));
    }

    public  float[] calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        return arr;
    }

    public  long countAll(float[] arr) {
        long a = System.currentTimeMillis();
            calculate(arr);
        return System.currentTimeMillis() - a;
    }

    private  long countInHalfbyThread(float[] arr) {
        float[] arrHalf1 = new float[HALF];
        float[] arrHalf2 = new float[HALF];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arrHalf1, 0, HALF);
        System.arraycopy(arr, HALF, arrHalf2, 0, HALF);
        long time1 = System.currentTimeMillis() - a;

        long b = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                calculate(arrHalf1);
            }
        });

        Thread t2 = new Thread(() -> calculate(arrHalf2));  //Через лямбду

        t1.start();
        t2.start();

        long time2 = System.currentTimeMillis() - b;

        long c = System.currentTimeMillis();
        System.arraycopy(arrHalf1, 0, arr, 0, HALF);
        System.arraycopy(arrHalf2, 0, arr, HALF, HALF);
        long time3 = System.currentTimeMillis() - c;

        long timeAll = time1 + time2 + time3;
        return timeAll;
    }

    private  long countInHalf(float[] arr) {
        float[] arrHalf1 = new float[HALF];
        float[] arrHalf2 = new float[HALF];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arrHalf1, 0, HALF);
        System.arraycopy(arr, HALF, arrHalf2, 0, HALF);
        long time1 = System.currentTimeMillis() - a;

        long b = System.currentTimeMillis();

                calculate(arrHalf1);
                calculate(arrHalf2);

        long time2 = System.currentTimeMillis() - b;

        long c = System.currentTimeMillis();
        System.arraycopy(arrHalf1, 0, arr, 0, HALF);
        System.arraycopy(arrHalf2, 0, arr, HALF, HALF);
        long time3 = System.currentTimeMillis() - c;

        long timeAll = time1 + time2 + time3;
        return timeAll;
    }
}