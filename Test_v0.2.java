package Test_v03;

import java.io.IOException;

public class Test {
	private static Calculations t;//创建算式生成类

	public static void main(String[] args) throws IOException {
		setT(new Calculations(50,100));//产生50道100以内的加减法
	}

	public static Calculations getT() {
		return t;
	}//返回值

	public static void setT(Calculations t) {
		Test.t = t;
	}//赋值
}
