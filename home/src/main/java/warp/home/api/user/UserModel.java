package warp.home.api.user;

import javax.validation.constraints.*;

public class UserModel {
	@Min(value = 1, message = "userId必须大于1")
	private long userId;
	@NotNull(message = "名字不能为空")
	private String userName;
	
	public long getUserId() { return userId;}
	public void setUserId(long id) {userId = id;}
	
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
}
