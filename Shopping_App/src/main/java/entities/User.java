package entities;

public class User {
	private String uid;
	private String pwd;
	private String fname;
	private String mname;
	private String lname;
	private String email;
	private String mono;
	
	public User(String uid,String pwd,String fname, String mname, String lname, String email, String mono) {
		super();
		this.uid=uid;
		this.pwd=pwd;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.email = email;
		this.mono = mono;
		
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMono() {
		return mono;
	}

	public void setMono(String mono) {
		this.mono = mono;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	

}
