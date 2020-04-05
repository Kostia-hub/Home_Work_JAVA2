public class Robot implements Skill {
    private int maxRunDistance;
    private int maxJumpHeigth;
    private String name;
    String type;

    public Robot(String name, int maxRunDistance, int maxJumpHeigth) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeigth = maxJumpHeigth;
        type = "Robot";
    }

    @Override
    public String  run(int distance) {
        if (distance <= this.maxRunDistance) {
            return  (type + " " + name + " Добежал!");
        } else {
            return  (type + " " + name + " не смог пробежать!");
        }
    }


    @Override
    public String jump(int height) {
        if (height <= this.maxJumpHeigth) {
            return  (type + " " + name + " справился с высотой!");
        } else {
            return  (type + " " + name + " не справился с высотой!");
        }
    }
}