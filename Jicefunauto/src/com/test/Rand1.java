package com.test;

import java.util.Random;

public class Rand1 {
	 public int getRandNum(int min, int max) {
		 Random random = new Random();
		 return random.nextInt(max) % (max - min + 1) + min;
		 }


}
