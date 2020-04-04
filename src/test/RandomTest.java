package test;

import BackEnd.OpenMode;
import BackEnd.RandomMode;
import FrontEnd.MyRed;

public class RandomTest {
    public static void main(String[] args) {
        MyRed red = new MyRed("发红包啦！");

        red.setOwnerName("王思聪");
        OpenMode random=new RandomMode();
        red.setOpenWay(random);
    }
}
