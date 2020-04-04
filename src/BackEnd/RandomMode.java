package BackEnd;

import java.util.ArrayList;
import java.util.Random;
/*实现发随机金额的红包模式*/
public class RandomMode implements OpenMode{
    @Override
    public ArrayList<Integer> divide(int totalMoney, int totalCount) {
        ArrayList<Integer> list=new ArrayList<>();
        Random r = new Random();

        int leftMoney=totalMoney;
        int leftCount=totalCount;

        for (int i = 0; i < totalCount; i++) {
//            多余的加到最后一个红包里
            if(i==totalCount-1){
                list.add(leftMoney);
            }
//            按照公式生成随机数，设置每个红包的金额限制
            int money=r.nextInt(leftMoney/leftCount*2)+1;
            list.add(money);
//            更新红包剩余金额和剩余个数
            leftCount--;
            leftMoney-=money;
        }
        return list;
    }
}
