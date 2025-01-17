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





package Test_v03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Calculations {


private Scanner input;


//构造随机数生成函数	
public int RandomNumberGeneration(int max) {
	Random r = new Random();
	return r.nextInt(max);
}

//构造算式范围判断函数
public boolean Judge(int x,int y) {
	if(x+y<100 && x-y>0){return true;}
	else {return false;}
}

//构造单个算式输出函数
public void TestPrint(int x,int y,String sign) {
		System.out.print("\t"+x+sign+y+"=\t");
	}	

//构造单行显示函数
public void Style(int number) {
	 if(number%5==0){
         System.out.println();
     }   
}

//构造答案生成函数——v0.2新增
public int Answer(int x,int y,String sign) {
	int sum;
	if(sign=="+") {
		sum= x+y;
	}else{
		sum= x-y;
	}
	return sum;
}

//构造答案保存函数——v0.2新增
public void AnswerSave(int i,int k) throws IOException {
	FileWriter fw=new FileWriter("D:\\AnswerSave.txt",true);
	String s = Integer.toString(k);
	fw.write("\t"+i+"、"+s+"\t");
	if(i%5==0) {
		fw.write("\n");
	}
	fw.close();
}

//构造答案输出函数——v0.2新增
public void AnswerPrint() throws IOException {
	BufferedReader in = new BufferedReader(new FileReader("D:\\AnswerSave.txt"));
	String s="";
	while((s=in.readLine())!=null)
	System.out.println(s);
	in.close();
}

//构造上次答案文件删除函数——v0.2新增
public void AnswerDelete() {
	File file = new File("D:\\AnswerSave.txt");
	file.delete();
}


public Calculations(int num,int max) throws IOException{
	
	
	//产生num道max以内的加减法
	System.out.print("请计算以下50道加减法：\n");
	for(int i=0 ; i<num ;){
	
		//通过产生随机数来随机产生加号或减号
		String sign = (RandomNumberGeneration(100)<50)?"+":"-";
            
		//随机运算数的产生
		int a = RandomNumberGeneration(max);
		int b = RandomNumberGeneration(max);
            
		//判断算式结果是否在0到100以内
		if(Judge(a,b) ){
			TestPrint(a,b,sign);
			int sum = Answer(a,b,sign);
			i++;//题目数计数单位
			
			//保存答案
			AnswerSave(i,sum);
	
            }else{
                continue;
            }
            
            //样式调整
            Style(i);
        }
        
        //判断是否查看答案
        System.out.print("是否查看答案？（YES/NO）\n");
        input = new Scanner(System.in);
		if(input.next().equalsIgnoreCase("YES")) {
			
			//打印答案
        	AnswerPrint();
        }else{
        	System.out.print("如果你对你的答案不确定，请重新计算一遍。");
        }
		
		//删除上次答案
		AnswerDelete();
    }
}
