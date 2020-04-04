package BackEnd;

import java.util.ArrayList;
/*
* 实现平均分配红包模式*/
public class NormalMode implements OpenMode{
    @Override
    public ArrayList<Integer> divide(int totalMoney, int count) {
        ArrayList<Integer> list=new ArrayList<>();

//        平均每个红包分到avg
        int avg=totalMoney/count;

        for (int i = 0; i < count; i++) {
//            多余的加到最后一个红包里
            if(i==count-1){
                list.add(avg+totalMoney%count);
            }
            list.add(avg);
        }
        return list;
    }
}
