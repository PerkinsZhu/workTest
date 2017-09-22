package com.zpj.other.package01;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
*
* ━━━━━━神兽出没━━━━━━
* 　　　┏┓　　　┏┓
* 　　┏┛┻━━━┛┻┓
* 　　┃　　　　　　　┃
* 　　┃　　　━　　　┃
* 　　┃　┳┛　┗┳　┃
* 　　┃　　　　　　　┃
* 　　┃　　　┻　　　┃
* 　　┃　　　　　　　┃
* 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
* 　　　　┃　　　┃    神兽保佑,代码无bug
* 　　　　┃　　　┃
* 　　　　┃　　　┗━━━┓
* 　　　　┃　　　　　　　┣┓
* 　　　　┃　　　　　　　┏┛
* 　　　　┗┓┓┏━┳┓┏┛
* 　　　　　┃┫┫　┃┫┫
* 　　　　　┗┻┛　┗┻┛
*
* ━━━━━━感觉萌萌哒━━━━━━
*/
 
/**
* 　　　　　　　　┏┓　　　┏┓
* 　　　　　　　┏┛┻━━━┛┻┓
* 　　　　　　　┃　　　　　　　┃ 　
* 　　　　　　　┃　　　━　　　┃
* 　　　　　　　┃　＞　　　＜　┃
* 　　　　　　　┃　　　　　　　┃
* 　　　　　　　┃...　⌒　...　┃
* 　　　　　　　┃　　　　　　　┃
* 　　　　　　　┗━┓　　　┏━┛
* 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting　　　　　　　　　　
* 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
* 　　　　　　　　　┃　　　┃　　　　　　　　　　　
* 　　　　　　　　　┃　　　┃  　　　　　　
* 　　　　　　　　　┃　　　┃
* 　　　　　　　　　┃　　　┃　　　　　　　　　　　
* 　　　　　　　　　┃　　　┗━━━┓
* 　　　　　　　　　┃　　　　　　　┣┓
* 　　　　　　　　　┃　　　　　　　┏┛
* 　　　　　　　　　┗┓┓┏━┳┓┏┛
* 　　　　　　　　　　┃┫┫　┃┫┫
* 　　　　　　　　　　┗┻┛　┗┻┛
*/
 
/**
*　　　　　　　　┏┓　　　┏┓+ +
*　　　　　　　┏┛┻━━━┛┻┓ + +
*　　　　　　　┃　　　　　　　┃ 　
*　　　　　　　┃　　　━　　　┃ ++ + + +
*　　　　　　 ████━████ ┃+
*　　　　　　　┃　　　　　　　┃ +
*　　　　　　　┃　　　┻　　　┃
*　　　　　　　┃　　　　　　　┃ + +
*　　　　　　　┗━┓　　　┏━┛
*　　　　　　　　　┃　　　┃　　　　　　　　　　　
*　　　　　　　　　┃　　　┃ + + + +
*　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting　　　　　　　
*　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug　　
*　　　　　　　　　┃　　　┃
*　　　　　　　　　┃　　　┃　　+　　　　　　　　　
*　　　　　　　　　┃　 　　┗━━━┓ + +
*　　　　　　　　　┃ 　　　　　　　┣┓
*　　　　　　　　　┃ 　　　　　　　┏┛
*　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
*　　　　　　　　　　┃┫┫　┃┫┫
*　　　　　　　　　　┗┻┛　┗┻┛+ + + +
*/

/** 
_______________#########_______________________ 
______________############_____________________ 
______________#############____________________ 
_____________##__###########___________________ 
____________###__######_#####__________________ 
____________###_#######___####_________________ 
___________###__##########_####________________ 
__________####__###########_####_______________ 
________#####___###########__#####_____________ 
_______######___###_########___#####___________ 
_______#####___###___########___######_________ 
______######___###__###########___######_______ 
_____######___####_##############__######______ 
____#######__#####################_#######_____ 
____#######__##############################____ 
___#######__######_#################_#######___ 
___#######__######_######_#########___######___ 
___#######____##__######___######_____######___ 
___#######________######____#####_____#####____ 
____######________#####_____#####_____####_____ 
_____#####________####______#####_____###______ 
______#####______;###________###______#________ 
________##_______####________####______________ 
 */ 
public class ABeautifulStory {
	private static class Person implements Runnable {
		private boolean isSingle = true;
		private String name;

		public void run() {
			while (true) {
				if (isSingle)
					System.out.println(name + "------ good good study, day day up !!!     /(ㄒoㄒ)/~~");
				else
					System.out.println(name + "------ unbelievable !!!  I'm in love  !!!    (づ￣3￣)づ╭❤～");
			}
		}

		public boolean wantToFallInLove(Person person) {
			boolean result = false;
			if ("Mr.Ming".equals(person.name) && "Miss .Hong".equals(this.name)) {
				System.out.println("Mr.Ming------ of course!!");
				result = true;
			} else if ("Miss .Hong".equals(person.name) && "Miss .Ming".equals(this.name)) {
				System.out.println("Miss .Hong------  OK ! I am waiting for you !!");
				result = true;
			}
			return result;
		}

		public boolean toStrollMeetAGirl() {
			boolean meetAGirl = false;
			System.out.println(this.name + "---- It's a nice day,go to the street to stroll.");
			System.out.println(this.name + "---- Who is this sweet  girl ?。。I am in love with her !!");
			meetAGirl = true;
			return meetAGirl;
		}

		public void goShopping() {
			System.out.println(this.name + "---- I am Shopping...............shopping..........");
		}

		public Person(String name) {
			this.name = name;
		}

		public boolean isSingle() {
			return isSingle;
		}

		public void setSingle(boolean isSingle) {
			this.isSingle = isSingle;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Person MrMing = new Person("Mr.Ming");
		Person MrssHong = new Person("Miss .Hong");
		Thread ming = new Thread(MrMing);
		Thread hong = new Thread(MrssHong);
		ming.start();
		hong.start();
		while (true) {
			
			if ("5:21".equals(new SimpleDateFormat("m:ss").format(new Date()).substring(1))) {
				MrssHong.goShopping();
				boolean meetAGirl = MrMing.toStrollMeetAGirl();
				if (meetAGirl) {
					ming.sleep(10000);
					hong.sleep(10000);
					boolean isSingle = MrMing.wantToFallInLove(MrssHong);
					if (!isSingle) {
						MrMing.setSingle(false);
						MrssHong.setSingle(false);
					}
				}
			}
		}
	}
}