package cn.sabercon.common.swagger;

import cn.sabercon.common.CommonConstant;
import cn.sabercon.common.domian.PageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * swagger 自定义配置
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .globalRequestParameters(buildGlobalParams())
                .select()
                .apis(RequestHandlerSelectors.basePackage(CommonConstant.BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return 全局自定义参数列表
     */
    private List<RequestParameter> buildGlobalParams() {
        // jwt token
        var token = new RequestParameterBuilder()
                .name("jwt-token")
                .description("认证 token")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build();
        return List.of(token);
    }

    /**
     * @return 接口主信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SaberCon API")
                .description("SaberCon Desc")
                .version("1.0.0")
                .build();
    }

    @Bean
    public AlternateTypeRuleConvention pageConvention() {
        return new AlternateTypeRuleConvention() {
            @Override
            public int getOrder() {
                return Ordered.HIGHEST_PRECEDENCE;
            }

            @Override
            public List<AlternateTypeRule> rules() {
                return List.of(AlternateTypeRules.newRule(ResolvableType.forClass(Page.class).getType(), ResolvableType.forClass(PageModel.class).getType()));
            }
        };
    }
}
