public class Main {
        public static void main(String[] args) {
            Human vasia = new Human("Vasia", 30, 2);
            Cat jorik = new Cat("Jorik", 3, 1);
            Robot terminator = new Robot("Terminator", 10, 3);

            Wall wall = new Wall(1);    //Не получилось, чтобы высота стены в методе jump бралась из wall
            Cross cross = new Cross(10);

            System.out.println(vasia.jump(2));
            System.out.println(jorik.jump(2));
            System.out.println(terminator.jump(2));

            System.out.println(vasia.run(20));
            System.out.println(jorik.run(20));
            System.out.println(terminator.run(22));

        }
}
