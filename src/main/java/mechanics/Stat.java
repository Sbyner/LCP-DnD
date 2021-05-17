package mechanics;

import java.util.function.Function;

public class Stat<T> {
	private T value;
	private boolean tempModified = false;
	private T tempValue;
	
	public Stat(T value){
		this.value = value;
		this.tempValue = value;
	}

	public void modify(Function<T, T> func) {
		tempValue = func.apply(value);
		tempModified = true;
	}
	
	public void permModify(Function<T, T> func) {
		value = func.apply(value);
	}

	public T getValue() {
		if (tempModified)
			return tempValue;
		else
			return value; 

	}
	 public void restoreValue() {
		 tempModified = false;
		 tempValue = value;
	 }
	 
	 @Override
	public String toString() {
		return getValue().toString();
	}

}
