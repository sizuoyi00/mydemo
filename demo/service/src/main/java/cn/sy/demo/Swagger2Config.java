package cn.sy.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket userApi() {

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("user")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.sy.demo"))
				.paths(PathSelectors.ant("/user/**"))
				.build()
				.globalOperationParameters(this.buildParameter());
	}

	@Bean
	public Docket sessionApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("session")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.sy.demo"))
				.paths(PathSelectors.ant("/session/**"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("springboot利用swagger构建api文档")
				.description("部署工具API接口 restful 风格接口")
				.termsOfServiceUrl("http://www.baidu.com")
				.version("1.0")
				.build();
	}

	/**
	 * 构建认证token参数
	 * @return token参数
	 */
	private List<Parameter> buildParameter( ){

		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("Authorization")
				.description("令牌")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build();
		pars.add(tokenPar.build());
		return  pars;
	}

}