package org.quantum.spin.entanglement.springboot;

import org.quantum.spin.entanglement.springboot.web.HelloController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/* Consider defining a bean of type 'org.quantum.spin.entanglement.springboot.service.posts.PostsService' in your configuration.
*  아래 테스트들은 sringboot package 안에 다른 연관된 package 들이 없고 떨어져 있을때 default 로 scan 하지 않기 때문에 component 들을 읽을 수 없는 경우가 생긴다.
*  그래서 처음 프로젝트를 만드는 경우에 아래와 같은 실수를 하는 경우가 생긴다.
* 스프링은 프로젝트 전체를 스캔하지 않고, @SpringBootApplcation 를 기준으로 스캔해서 Bean 대상에 포함시키는데요.
  서비스 패키지가 외부에 있다보니 읽어오질 못해서 발생한 에러입니다.
  다른 패키지와 마찬가지로 하위 패키지로 이동시켜보시면 될것 같습니다.*/
/* 같은 package 안에 프로젝트 파일들이 없을 경우에 아래와 같은 세팅으로도 가능하긴 하다고 확인되어진다.
* You can either restructure your packages such that "WebServiceApplication" falls under a root package and
* all other components becomes part of that root package. Or you can include @SpringBootApplication(scanBasePackages={"com.service.something","com.service.application"}) etc such that
* "ALL" components are scanned and initialized in the spring container.
* */


//@ComponentScan(basePackageClasses = HelloController.class)
//@ComponentScan(basePackages = "org.quantum.spin.entanglement.springboot")
//@EnableAutoConfiguration
//@ComponentScan

@EnableSwagger2
@EnableJpaAuditing // JPA Auditing 활성화.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
