public class User {
    private int id;
    private String name;
    private String Address;
    private int CourseId;
    private int level;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        this.CourseId = courseId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
