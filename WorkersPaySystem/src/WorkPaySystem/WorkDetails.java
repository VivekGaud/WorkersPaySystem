package WorkPaySystem;

public class WorkDetails {
	private int workid;
	private int workerid;
	private String date;
	private int type;
	private int piece;
	private float rate;
	private float rupee;
	
	
	
	public WorkDetails(int workid,int workerid,String date1,int type,int piece,float rate,float rupee){
		this.workid = workid;
		this.workerid = workerid;
		this.date = date;
		this.type = type;
		this.piece = piece;
		this.rate = rate;
		this.rupee = rupee;
		
	}



	public int getWorkid() {
		return workid;
	}



	public void setWorkid(int workid) {
		this.workid = workid;
	}



	public int getWorkerid() {
		return workerid;
	}



	public void setWorkerid(int workerid) {
		this.workerid = workerid;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getPiece() {
		return piece;
	}



	public void setPiece(int piece) {
		this.piece = piece;
	}



	public float getRate() {
		return rate;
	}



	public void setRate(float rate) {
		this.rate = rate;
	}



	public float getRupee() {
		return rupee;
	}



	public void setRupee(float rupee) {
		this.rupee = rupee;
	}
}
