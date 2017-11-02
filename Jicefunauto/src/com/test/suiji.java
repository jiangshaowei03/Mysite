package com.test;

public class suiji extends Rand1 {

	public   void main(String [] args){
		
	int X = getRandNum(0, 14);
	
	if(X==2&&X==5&&X==6)
	{
		int Z = getRandNum(X + 1, 14);
		if (Z == 5 && Z == 6) {
			int N = getRandNum(Z + 1, 14);
			if (N == 6) {
				int O = getRandNum(N + 1, 14);
				System.out.println("当前的随机数值：" + O);

			} else {
				System.out.println("当前的随机数值：" + Z);

			}
		}
	}else
	{
		System.out.println("当前的随机数值：" + X);
	}
}
	}