import java.io.Serializable;

@SuppressWarnings("serial")
public class Record implements Serializable {
	String Mode;    //Mode
	String Dur;    //Duration
	String Tom;    //Type of meal
	String Des;     //meal Description
	
	public Record() {
		super();
	}

	public Record(String mode, String dur, String tom, String des) {
		super();
		Mode = mode;
		Dur = dur;
		Tom = tom;
		Des = des;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getDur() {
		return Dur;
	}

	public void setDur(String dur) {
		Dur = dur;
	}

	public String getTom() {
		return Tom;
	}

	public void setTom(String tom) {
		Tom = tom;
	}

	public String getDes() {
		return Des;
	}

	public void setDes(String des) {
		Des = des;
	}
}
