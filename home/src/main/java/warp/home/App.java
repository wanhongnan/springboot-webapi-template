package warp.home;

import org.springframework.boot.SpringApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan()
@SpringBootApplication(scanBasePackages = {"warp.home","warp.home.api.user"})
@EnableSwagger2()
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class,args);
    }
}
