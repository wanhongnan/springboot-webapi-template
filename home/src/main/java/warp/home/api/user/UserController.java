package warp.home.api.user;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import warp.home.core.ResultException;

@Validated()
@RestController()
@RequestMapping("/api/user")
@Api("User相关api")
public class UserController {

	@ApiOperation(value = "通过用户id读取用户数据")
    @GetMapping("/{id}")
    public UserModel getUserById(
    		@Min(value = 1,message = "id必须大于1")
    		@PathVariable long id) throws ResultException {
    	UserModel ret = new UserModel();
    	ret.setUserId(id);
    	ret.setUserName("south");
    	
    	throw new ResultException(222,"test error",null);
        //return ret;
    }

	@ApiOperation(value = "创建用户")
    @PostMapping
    public UserModel createUser(@Valid@RequestBody UserModel user) {
        System.err.println("create an user: " + user);
        return user;
    }
}

