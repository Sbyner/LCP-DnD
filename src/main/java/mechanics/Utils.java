package mechanics;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

public abstract class Utils {
	public enum Advantage{
		ADVANTAGE,
		NO,
		DISADVANTAGE
		
	}
	
	public static Advantage increaseAdvantage(Advantage advantage) {
		switch (advantage) {
		    case ADVANTAGE: return Advantage.ADVANTAGE;
		    case NO: return Advantage.ADVANTAGE;
		    case DISADVANTAGE: return Advantage.NO;
		    default: return advantage;
		}
	}
	
	public static Advantage decreaseAdvantage(Advantage advantage) {
		switch (advantage) {
		    case ADVANTAGE: return Advantage.NO;
		    case NO: return Advantage.DISADVANTAGE;
		    case DISADVANTAGE: return Advantage.DISADVANTAGE;
		    default: return advantage;
		}
	}
	
	public static int roll(String value, Advantage advantage) {
		Pattern pattern = Pattern.compile("^(\\d+)d(\\d+)$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(value);
	    matcher.matches();
	    int diceCount = Integer.parseInt(matcher.group(1));
	    int dieValue = Integer.parseInt(matcher.group(2));
	    Stream<Integer> stream = new Random().ints(1, dieValue+1).mapToObj(Integer::new);
	    switch(advantage) {
	    case ADVANTAGE: {
	    	Stream<Integer> stream2 = new Random().ints(1, dieValue+1).mapToObj(Integer::new);
	    	return Streams.zip(stream, stream2, (x,y)->{return Math.max(x, y);}).limit(diceCount).reduce(0, (x,y)->{return x+y;});
	    }
	    case DISADVANTAGE: {
	    	Stream<Integer> stream2 = new Random().ints(1, dieValue+1).mapToObj(Integer::new);
	    	return Streams.zip(stream, stream2, (x,y)->{return Math.min(x, y);}).limit(diceCount).reduce(0, (x,y)->{return x+y;});
	    }
	    default: return stream.limit(diceCount).reduce(0, (x,y)->{return x+y;});
	    }
	    
	}
	
	public static int calculateBonus(int abilityScore) {
		return (abilityScore/2)-5;
	}
}
