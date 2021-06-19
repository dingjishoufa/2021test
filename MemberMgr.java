import java.text.SimpleDateFormat;
import java.util.*;

public class MemberMgr {
    //所有会员
    List<Member> memberList=new ArrayList<Member>();
    Scanner scanner=new Scanner(System.in);

    //输出欢迎菜单
    public void menu(){
        System.out.println("***************欢迎使用超市会员管理系统***************");
        System.out.println("1.开卡     2.修改密码     3.积分累计     4.积分兑换     5.剩余积分查询     6.退出");
        System.out.println("*************************************************");
        System.out.print("请选择：");
    }
    //搭建项目整体流程
    public void start(){
        do {
            menu();
            int choose=scanner.nextInt();
            switch (choose){
                case 1:
                    register();
                    continue;
                case 2:
                    if (changePwd()){
                        System.out.println("新密码设置成功");
                    }
                    continue;
                case 3:
                    if (saveScore()){
                        System.out.println("积分累计成功");
                    }
                    continue;
                case 4:
                    if (minuScore()){
                        System.out.println("积分兑换成功");
                    }
                    continue;
                case 5:
                    showScore();
                    continue;
                case 6:
                    System.out.println("已退出，欢迎下次使用");
                    break;
                default:
                    System.out.print("您的选择有误，请重新选择：");
                    continue;
            }
            break;
        }while (true);
    }
    //随机生成一个会员卡号
    public int createId(){
        Random random=new Random();
        int id=random.nextInt(99999999);
        for (Member m:memberList){
            if (id==m.getCardId()){
                //生成的会员卡号不能相同
                id=random.nextInt(99999999);
            }
        }
        return id;
    }
    //开卡
    public void register(){
        Member member=new Member();
        System.out.print("请输入注册姓名：");
        member.setName(scanner.next());
        System.out.print("请输入注册密码：");
        String pwd;
        boolean flag=true;
        do {
            pwd=scanner.next();
            if (pwd.length()<6){
                System.out.print("会员密码不能小于6位，请重新输入");
                flag=false;
            }else {
                flag=true;
                member.setPassword(pwd);
            }
        }while (!flag);
        member.setCardId(createId());
        member.setScore(100);
        //开卡日期
        Date date=new Date();
        SimpleDateFormat formater=new SimpleDateFormat("MM月dd日");
        String cardDate=formater.format(date);
        member.setRegistDate(cardDate);

        //将注册成功的会员卡号添加到会员集合中，并提示开卡成功
        memberList.add(member);
        System.out.println("恭喜，开通会员卡号成功，系统赠送您100积分，您的会员卡号为："+member.getCardId());
    }
    //验证会员是否存在
    //查询条件（会员卡号和密码）--封装在参数m中传过来
    public Member hasMember(Member m){
        Member member=null;
        for (Member everyMember:memberList){
            if (m.getCardId()==everyMember.getCardId()&&
                    m.getPassword().equals(everyMember.getPassword())){
                member=everyMember;
            }
        }
        return member;
    }
    //修改密码
    public boolean changePwd(){
        boolean flag=true;
        Member member=new Member();
        System.out.print("请输入您的会员卡号：");
        member.setCardId(scanner.nextInt());
        System.out.print("请输入您的会员密码：");
        member.setPassword(scanner.next());

        member=hasMember(member);//member如果找到了，就是找到了会员对象，如果找不到，mamber就是null
        if (member!=null){
            //会员卡号和密码正确，找到该会员，可以修改密码
            System.out.print("请输入您新的会员密码：");
            String pwd;
            boolean flagPwd=true;
            do {
                pwd=scanner.next();
                if (pwd.length()<6){
                    System.out.println("会员密码不能小于6位，请重新输入会员密码：");
                    flagPwd=false;
                }else {
                    member.setPassword(pwd);
                    flagPwd=true;
                    flag=true;
                }
            }while (!flagPwd);
        }else {
            System.out.println("您输入的会员卡号或密码有无，无法修改密码");
            flag=false;
        }
        return flag;
    }
    //积分累计
    public boolean saveScore(){
        boolean flag=true;
        Member member=new Member();
        System.out.print("请输入您的会员卡号：");
        member.setCardId(scanner.nextInt());
        System.out.print("请输入您的会员密码：");
        member.setPassword(scanner.next());

        member=hasMember(member);//member如果找到了，就是找到了会员对象，如果找不到，mamber就是null
        if (member!=null){
            //该会员存在，可以累计积分
            System.out.print("请输入您此次消费的金额（消费一元累计一积分）：");
            int score=scanner.nextInt();
            member.setScore(member.getScore()+score);
            flag=true;
        }else {
            System.out.println("您输入的会员卡号或密码错误，无法累计积分");
            flag=false;
        }
        return flag;
    }
    //查询剩余积分
    public void showScore(){
        Member member=new Member();
        System.out.print("请输入您的会员卡号：");
        member.setCardId(scanner.nextInt());
        System.out.print("请输入您的会员密码：");
        member.setPassword(scanner.next());

        member=hasMember(member);
        if (member!=null){
            System.out.println("姓名\t会员卡号\t\t剩余积分\t\t开卡日期");
            System.out.println(member.getName()+"\t"+member.getCardId()+"\t"+member.getScore()+"\t\t"+member.getRegistDate());
        }else {
            System.out.println("对不起，您输入的会员卡号或密码错误，无法查询");
        }
    }
    //积分兑换
    public boolean minuScore(){
        boolean flag=true;
        Member member=new Member();
        System.out.print("请输入您的会员卡号：");
        member.setCardId(scanner.nextInt());
        System.out.print("请输入您的会员密码：");
        member.setPassword(scanner.next());
        member=hasMember(member);
        if (member!=null){
            //会员存在，兑换积分 （1）修改会员现有积分 （2）按照积分兑换规则给用户提示
            System.out.print("请输入您要兑换的积分（100积分抵用0.1元，不足100的积分不能抵用）");
            int score=scanner.nextInt();
            if (score<member.getScore()){
                member.setScore(member.getScore()-score);
                System.out.println("您的消费金额中使用会员积分抵扣了"+score/100*0.1+"元");
                flag=true;
            }else {
                System.out.println("抱歉，您的积分不够，无法抵用");
                flag=false;
            }
        }else {
            System.out.println("对不起，您输入的会员卡号或密码错误，无法积分兑换");
            flag=false;
        }
        return flag;
    }

}
