import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        //创建用户集合
        ArrayList<User> list = new ArrayList<>();
        //新建Scanner对象
        Scanner sc = new Scanner(System.in);

        loop : while (true) {
            //交互界面
            System.out.println("---------------欢迎来到学生管理系统---------------");
            System.out.println("1登录 2注册 3忘记密码 4退出\n请输入操作：");
            //键入选项
            String choose = sc.next();
            //判断键入选项
            switch (choose){
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> findBackPwd(list);
                case "4" -> {
                    System.out.println("退出 ");
                    break loop;
                }
                default -> System.out.println("没有这个选项，请重新输入！");
            }
        }
    }

    //登录
    public static void login(ArrayList<User> list){
        //判断集合是否为空
        if (list.size() == 0){
            System.out.println("当前无用户注册，请先注册！");
            return;
        }
        //新建一个Scanner
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 3; i++) {
            //用户名
            System.out.println("请输入用户名：");
            String name = sc.next();
            //获取索引
            int index = getIndex(list,name);
            if (index == -1){
                System.out.println("用户名" + name + "未注册，请先注册再登录。");
                return;
            }

            //密码
            System.out.println("请输入密码：");
            String pwd = sc.next();

            //验证码
            while (true) {
                String confirmCode = getConfirmCode();
                System.out.println("当前验证码为：" + confirmCode);
                System.out.println("请输入验证码：");
                String inputCode = sc.next();
                if (confirmCode.equalsIgnoreCase(inputCode)){
                    //登录成功
                    System.out.println("验证码正确！");
                    break;
                }else {
                    System.out.println("验证码错误！");
                }
            }

            //验证密码
            //封装
            User user = new User(name,pwd,null,null);
            boolean flag = checkUserInfo(list,user);
            if (flag){
                System.out.println("登录成功！");
                //创建对象，启用方法，启动学生管理系统
                StudentSystem ss = new StudentSystem();
                ss.startStudentSystem();
                break;
            }else {
                System.out.println("登录失败，用户名或密码错误！");
                if (i == 3){
                    System.out.println("当前帐号" + name + "被锁定，请联系管理员！");
                    return;
                }else {
                    System.out.println("还剩下" + (3 - i) + "次机会！");
                }
            }

        }
    }

    //注册
    public static void register(ArrayList<User> list){
        //新建一个User对象
        User user = new User();
        //新建一个Scanner
        Scanner sc = new Scanner(System.in);

        //用户名
        while (true) {
            //键盘录入用户名
            System.out.println("请输入一个新的用户名：");
            String newName = sc.next();
            //检查用户名
            boolean flag = checkUsername(list,newName);
            if (flag){
                //判断是否已存在
                if (!contains(list,newName)){
                    //符合条件
                    user.setUserName(newName);
                    break;
                }else {
                    //用户名已存在
                    System.out.println("用户名已存在，再取一个吧。");
                }
            }else {
                //用户名格式错误
                System.out.println("用户名格式错误，再取一个吧。");
            }
        }

        //密码
        while (true){
            System.out.println("请输入新密码：");
            String pwd = sc.next();
            System.out.println("请再次输入密码：");
            String pwdA = sc.next();
            if (pwd.equals(pwdA)){
                //两次密码一致
                user.setPassword(pwd);
                break;
            }else {
                System.out.println("两次输入的密码不一致，请重新输入。");
            }
        }

        //身份证号
        while (true){
            System.out.println("请输入身份证号：");
            String id = sc.next();
            boolean flag = checkID(id);
            if (flag){
                //身份证号符合要求
                user.setID(id);
                break;
            }else {
                System.out.println("身份证号格式错误，请重新输入。");
            }
        }

        //手机号
        while (true){
            System.out.println("请输入手机号：");
            String phoneNum = sc.next();
            boolean flag = checkPhoneNum(phoneNum);
            if (flag){
                //手机号符合条件
                user.setPhoneNum(phoneNum);
                break;
            }else {
                System.out.println("手机号格式错误，请重新输入。");
            }
        }

        //将新建用户加入集合
        list.add(user);
        System.out.println("注册成功！");
    }

    //忘记密码
    public static void findBackPwd(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        //键入需要找回的用户名
        System.out.println("请输入要找回用户的用户名：");
        String name = sc.next();
        //获得索引
        int index = getIndex(list,name);
        //判断是否存在
        if (index == -1){
            //不存在
            System.out.println("用户不存在，请先注册！");
            return;
        }

        //执行到此处，即用户存在
        User user = list.get(index);
        //键入身份证号
        System.out.println("请输入身份证号：");
        String id = sc.next();;
        //键入手机号
        System.out.println("请输入手机号：");
        String phoneNum = sc.next();

        //验证身份证号和手机号
        if (!(user.getID().equalsIgnoreCase(id) && user.getPhoneNum().equals(phoneNum))){
            System.out.println("身份证号或手机号输入有误，不能修改密码！");
            return;
        }else System.out.println("验证成功！");

        //验证成功，修改密码
        String newPwd;
        while (true) {
            System.out.println("请输入新的密码：");
            newPwd = sc.next();
            System.out.println("请再次输入新的密码：");
            String newPwdA = sc.next();
            //校验新密码
            if (newPwd.equals(newPwdA)){
                System.out.print("两次密码一致，");
                break;
            }else {
                System.out.println("两次密码不一致，请重新输入！");
                continue;
            }
        }
        //修改密码
        user.setPassword(newPwd);
        //修改成功
        System.out.println("修改成功！");
    }

    //判断用户是否已存在
    public static boolean contains(ArrayList<User> list, String name){
        //大于等于0即存在，小于0即不存在
        return getIndex(list,name) >= 0;
    }

    //获取元素索引
    public static int getIndex(ArrayList<User> list, String name){
        //遍历集合
        for (int i = 0; i < list.size(); i++) {
            //获取用户名
            String Uname = list.get(i).getUserName();
            //对比，一致则返回索引
            if (Uname.equals(name)){
                return i;
            }
        }

        //目标用户不存在，返回-1
        return -1;
    }

    //检查身份证号
    public static boolean checkID(String ID){
        //判断长度是否为18、首位是否为0
        if (ID.length() != 18 || ID.startsWith("0")){
            return false;
        }
        //遍历前17位，判断是否都为数字
        for (int i = 0; i < 17; i++) {
            char c = ID.charAt(i);
            if (c < '0' || c > '9'){
                return false;
            }
        }
        //执行到这里，已满足18位，首位不为0，前17位为数字
        //判断最后一位
        char endChar = ID.charAt(17);
        if (endChar == 'x' || endChar == 'X'
            || (endChar >= '0' && endChar <= '9')){
            return true;
        }
        //执行到这里，第18为不符合
        return false;
    }

    //检查手机号
    public static boolean checkPhoneNum(String PhoneNum){
        //判断是否为11位，或以0位开头
        if (PhoneNum.length() != 11 || PhoneNum.startsWith("0")){
            return false;
        }
        //判断是否都为数字
        for (int i = 0; i < PhoneNum.length(); i++) {
            if (PhoneNum.charAt(i) < '0' || PhoneNum.charAt(i) > '9'){
                return false;
            }
        }
        //执行到此处，符合所有条件
        return true;
    }

    //检查新用户名的格式
    public static boolean checkUsername(ArrayList<User> list, String name){
        //判断长度
        int length = name.length();
        if (length < 3 || length > 15){
            return false;
        }
        //判断字符合法
        for (int i = 0; i < name.length(); i++) {
            //获取字符
            char c = name.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))){
                return false;
            }
        }
        //执行到这里，说明由数字或字母组成
        //判断是否只有数字
        int count = 0;  //统计字母的个数
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                count++;
                break;
            }
        }
        //只有数字返回false
        //返回true代表符合所有条件
        return count > 0;
    }

    //判断用户名和密码是否一致
    public static boolean checkUserInfo(ArrayList<User> list,User user){
        //遍历集合，增强的for循环
        for (User user1 : list) {
            if (user1.getUserName().equals(user.getUserName()) && user1.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    //获取验证码
    private static String getConfirmCode(){
        //1. 数字放在一个数组中，大小写字母放到一个数组中
        char[] nums = {'0','1','2','3','4','5','6','7','8','9'};
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }

        //2. 循环5次，生成验证码，只有一个数字
        char[] result = new char[5];
        Random random = new Random();
        for (int i = 0; i < result.length - 1; i++) {
            int rdmCharIndex = random.nextInt(list.size());
            result[i] = list.get(rdmCharIndex);
        }
        int rdmNumIndex = random.nextInt(10);
        result[4] = nums[rdmNumIndex];

        //3. 打乱result数组
        for (int i = 0; i < result.length; i++) {
            int Index = random.nextInt(result.length);
            char temp = result[i];
            result[i] = result[Index];
            result[Index] = temp;
        }

        //4. 生成String
        return new String(result);
    }
}