import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void main(String[] args) {
        //创建学生集合
        ArrayList<Student> list = new ArrayList<>();

        //进入系统
        loop : while (true) {
            //欢迎界面
            System.out.println("---------------学生管理系统---------------");
            System.out.println("1:添加学生信息");
            System.out.println("2:删除学生信息");
            System.out.println("3:修改学生信息");
            System.out.println("4:查询学生信息");
            System.out.println("5:退出查询系统");
            System.out.println("请输入您的选择:");

            //键入选择
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            //判断选择
            switch (choose){
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> updateStudent(list);
                case "4" -> queryStudent(list);
                case "5" -> {
                    System.out.println("退出");
                    break loop;   //法1：跳出loop循环
                    //System.exit(0);    //法2：停止虚拟机运行
                }
                default -> System.out.println("没有这个选项！");
            }
        }
    }

    //添加学生
    public static void addStudent(ArrayList<Student> list){
        //创建一个学生对象
        Student  stu = new Student();

        //开始键盘录入
        Scanner sc = new Scanner(System.in);

            //id
        while (true) {
            System.out.println("请输入学生id:");
            long id = sc.nextLong();
            //判断id是否已存在
            boolean flag = contains(list,id);
            if (flag){
                //录入id已存在
                System.out.println(id + "已存在，请重新录入！");
            }else {
                //录入id不存在
                stu.setId(id);
                break;
            }
        }

        //姓名
        System.out.println("请输入学生姓名:");
        String name = sc.next();
        stu.setName(name);

            //年龄
        System.out.println("请输入学生年龄:");
        int age = sc.nextInt();
        stu.setAge(age);

            //家庭住址
        System.out.println("请输入学生家庭住址:");
        String address = sc.next();
        stu.setAddress(address);

        //添加学生到集合中
        list.add(stu);

        //提示添加成功
        System.out.println("添加成功！");
    }

    //删除学生
    public static void deleteStudent(ArrayList<Student> list){
        //键入要删除的id
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除学生的id：");
        long id = sc.nextLong();
        //判断id是否存在
        int index = getIndex(list,id);  //id对应索引
        if (index >= 0){
            //存在，执行删除
            list.remove(index);
            System.out.println("id为：" + id + "的学生删除成功！");
        }else {
            //不存在
            System.out.println(id + "不存在，删除失败");
        }
    }

    //修改学生
    public static void updateStudent(ArrayList<Student> list){
        //键入要修改的学生id
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生id：");
        long id = sc.nextLong();

        //获得键入id索引
        int index = getIndex(list,id);

        //判断id是否存在
        if (index == -1){
            //id不存在
            System.out.println("不存在id为" + id + "的学生。");
            return;
        }

        //id存在，继续执行以下语句

        //获得学生对象
        Student stu = list.get(index);

        //更改学生姓名
        System.out.println("请输入新的姓名：");
        String newName = sc.next();
        stu.setName(newName);

        //更改学生年龄
        System.out.println("请输入新的年龄：");
        int newAge = sc.nextInt();
        stu.setAge(newAge);

        //更改学生家庭住址
        System.out.println("请输入新的家庭住址：");
        String newAddress = sc.next();
        stu.setAddress(newAddress);

        //提示修改完成
        System.out.println("信息修改完成！");
    }

    //查询学生
    public static void queryStudent(ArrayList<Student> list){
        if (list.size() == 0){
            System.out.println("当前无学生信息，请添加后再查询!");
            return;
        }

        //打印标头
        System.out.println("id\t\t姓名\t年龄\t家庭住址");
        //当集合内有元素时，继续执行以下代码
        //增强的for循环
        for (Student stu : list) {
            //获取学生对象
            //打印查询结果
            System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
        }
    }

    //判断id是否存在
    public static boolean contains(ArrayList<Student> list, long id){
/*
        //遍历集合，得到每一个学生对象
        //使用增强for循环
        for (Student stu : list) {
            //获得学生对象
            long stu_id = stu.getId();  //获得学生id

            //比较录入id与学生id是否相同
            if (stu_id == id) {
                //id已存在
                return true;
            }
        }

        //录入id在集合中不存在，执行以下语句
        return false;
*/
        return getIndex(list, id) >= 0;
    }

    //通过id，寻找索引
    public static int getIndex(ArrayList<Student> list, long id){
        //遍历集合，比较id
        for (int i = 0; i < list.size(); i++) {
            //获得学生对象
            Student stu = list.get(i);
            //获得学生id
            long stu_id = stu.getId();
            //比较当前学生id与查询id
            if (id == stu_id){
                //当前学生id与查询id相同
                return i;
            }
        }
        return -1;
    }
}