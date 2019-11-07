package poker;

import java.util.*;

public class Poker {
	
	static int randomCard;
	static int maxRandomCount = 52;
	static float onePare = 0;
	static float twoPares = 0;
	static float threeOfAKind = 0;
	static float straight = 0;
	static float flush = 0;
	static float fullHouse = 0;
	static float fourOfAKind = 0;
	static float straightFlush = 0;
	static float royalFlush = 0;
	static int repeats;
	static String games;
	static int scan;
	static int [] cards = new int [52];
	static int [] hand = new int [5];
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		games =  scan.next();
		repeats = Integer.valueOf(games); 
		
		for(int i = 0; i <= repeats; i++) {
			fillCards(cards);
			fillHand(hand, cards);
			checkOnePare();
			checkTwoPare();
			checkThreeOfAKind();
			checkStraight();
			checkFlush();
			checkFullHouse();
			checkFourOfAKind();
			checkStraightFlush();
			checkRoyalFlush();
		}
		ausgabe();
	}
	
		static public void fillCards(int cards[]) {
			for(int i = 0; i < cards.length; i++) {
				cards[i] = i;
			}
		}
		
		static public void fillHand(int hand[], int cards[]) {
			for (int i = 0; i < hand.length; i++) {
				randomCard = (int) (maxRandomCount * Math.random());
				hand[i] = cards[randomCard];
				cards[randomCard] = cards[maxRandomCount-1];
				maxRandomCount = maxRandomCount -1;
			}
			maxRandomCount = 52;
		}
		
		static public void checkOnePare() {
			for(int i = 0; i < hand.length-1; i++) {
				for(int j = 1; j < hand.length; j++) {
					if (i+j <= 4) {
						if((hand[i] - hand[i+j]) % (cards.length/4) == 0) onePare++;
					}
				}
			}
		}
		
		static public void checkTwoPare() {
			float pares = onePare;
			checkOnePare();
			if(onePare == pares+1) onePare = onePare -1;
			if(onePare == pares+2) {
				onePare = onePare -2;
				twoPares++;
				}
		}
		
		static public void checkThreeOfAKind() {
			for(int i = 0; i < hand.length-1; i++) {
				for(int j = 1; j < hand.length; j++) {
					for(int x = 2; x < hand.length; x++) {
						if((i+j <= hand.length-1) && (i+x <= hand.length-1)) {
							if(((hand[i] - hand[i+j]) % (cards.length/4) == 0)
							&& ((hand[i+j] - hand[i+x]) % (cards.length/4) == 0)) {
								threeOfAKind++;
							}
						}
					}
				}
			}
		}
		
		static public void checkStraight() {
			int z = 0;
			for(int i = 0; i < hand.length; i++) {
				for(int j = 0; j < hand.length; j++) { 
					if((hand[i] % (cards.length/4)) == (hand[j] % (cards.length/4))+1) z++;
					if(hand[i] == (cards.length/4)-1) {
						int x = hand[i];
						hand[i] = -1;
						if((hand[i] % (cards.length/4)) == (hand[j] % (cards.length/4))+1) z++;
						hand[i] = x;
					}
					if(hand[j] == (cards.length/4)-1) {
						int y = hand[i];
						hand[j] = -1;
						if((hand[i] % (cards.length/4)) == (hand[j] % (cards.length/4))+1) z++;
						hand[j] = y;
					}
				}
				if(z == hand.length-1) straight++;
			}
		}
		
		static public void checkFlush() {
			int z = 0;
			if((hand[0] >= 0) && (hand[0] <= ((cards.length/4)-1))) {
				for(int i = 1; i < hand.length; i++) {
					if((hand[i] >= 0) && (hand[i] <= ((cards.length/4)-1))) z++;
				}
			}
			if((hand[0] >= cards.length/4) && (hand[0] <= ((cards.length/2)-1))) {
				for(int i = 1; i < hand.length; i++) {
					if((hand[i] >= cards.length/4) && (hand[i] <= ((cards.length/2)-1))) z++;
				}
			}
			if((hand[0] >= cards.length/2) && (hand[0] <= (((cards.length/2) + (cards.length/4))-1))) {
				for(int i = 1; i < hand.length; i++) {
					if((hand[i] >= cards.length/2) && (hand[i] <= (((cards.length/2) + (cards.length/4))-1))) z++;
				}
			}
			if((hand[0] >= ((cards.length/2) + (cards.length/4))) && (hand[0] <= (cards.length-1))) {
				for(int i = 1; i < hand.length; i++) {
					if((hand[i] >= ((cards.length/2) + (cards.length/4))) && (hand[i] <= (cards.length-1))) z++;
				}
			}
			if(z == hand.length-1) flush++;
		}
		
		static public void checkFullHouse() {
			float pare = onePare;
			float three = threeOfAKind;
			checkOnePare();
			checkThreeOfAKind();
			if((onePare == pare +1) && (threeOfAKind == three +1)) {
				onePare = onePare -1;
				threeOfAKind = threeOfAKind -1;
				fullHouse++;
			}
			if(onePare == pare +2) onePare = onePare -2;
		}
		
		static public void checkFourOfAKind() {
			for(int i = 0; i < hand.length; i++) {
				for(int j = 1; j < hand.length; j++) {
					for(int x = 2; x < hand.length; x++) {
						for(int y = 3; y < hand.length; y++) {
							if((i+j <= hand.length-1) && (i+x <= hand.length-1) && (i+y <= hand.length-1)) {
								if(((hand[i] - hand[i+j]) % (cards.length/4) == 0)
								&& ((hand[i+j] - hand[i+x]) % (cards.length/4) == 0)
								&& ((hand[i+x] - hand[i+y]) % (cards.length/4) == 0)) {
									fourOfAKind++;
								}
							}
						}	
					}
				}
			}
		}
		
		static public void checkStraightFlush() {
			float s = straight;
			float f = flush;
			checkStraight();
			checkFlush();
			if((straight == s+1) && (flush == f+1)) straightFlush++;
			if(straight == s+1) straight = straight-1;
			if(flush == f+1) flush = flush-1;
		}
		
		static public void checkRoyalFlush() {
			float f = flush;
			int z = 0;
			checkFlush();
			for(int i = 0; i < hand.length; i++) {
				if(hand[i] % (cards.length/4) >= 8) {
					for(int j = 0; j < hand.length; j++) {
						if(hand[j] % (cards.length/4) >= 8) {
							if((hand[i] % (cards.length/4)) == (hand[j] % (cards.length/4))+1) z++;
						}
					}
				}
			}
			if((flush == f+1) && (z == hand.length-1)) royalFlush++;
			if(flush == f+1) flush = flush-1;
		}
		
		static public void ausgabe() {
			System.out.println("OnePare: "+(onePare/repeats)*100);
			System.out.println("TwoPares: "+(twoPares/repeats)*100);
			System.out.println("ThreeOfAKind: "+(threeOfAKind/repeats)*100);
			System.out.println("Straight: "+(straight/repeats)*100);
			System.out.println("Flush: "+(flush/repeats)*100);
			System.out.println("FullHouse: "+(fullHouse/repeats)*100);
			System.out.println("FourOfAKind: "+(fourOfAKind/repeats)*100);
			System.out.println("StraightFlush: "+(straightFlush/repeats)*100);
			System.out.println("RoyalFlush: "+(royalFlush/repeats)*100);
		}
	}
