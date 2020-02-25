package sec03.ex02;

public class MemberVO {
	
	private String prod_id;
	private String prod_name;
	private Double prod_price;
	private String prod_desc;
	private String vend_id;
	private String vend_name;
	

	public String getVend_name() {
		return vend_name;
	}

	public void setVend_name(String vend_name) {
		this.vend_name = vend_name;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public Double getProd_price() {
		return prod_price;
	}

	public void setProd_price(Double prod_price) {
		this.prod_price = prod_price;
	}

	public String getProd_desc() {
		return prod_desc;
	}

	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}

	public String getVend_id() {
		return vend_id;
	}

	public void setVend_id(String vend_id) {
		this.vend_id = vend_id;
	}

	public MemberVO() {
		System.out.println("MemberVO 생성자 호출");
	}

	public MemberVO(String prod_id,String vend_id, String prod_name, Double prod_price, String prod_desc) {
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_desc = prod_desc;
		this.vend_id = vend_id;
	}
	
	public MemberVO(String prod_id, String prod_name, Double prod_price, String prod_desc,String vend_name)
	{
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_desc = prod_desc;
		this.vend_name = vend_name;
	}
}
