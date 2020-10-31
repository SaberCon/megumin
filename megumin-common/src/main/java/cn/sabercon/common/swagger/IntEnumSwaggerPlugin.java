package cn.sabercon.common.swagger;

import cn.sabercon.common.enums.IntEnum;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.builders.ExampleBuilder;
import springfox.documentation.builders.ModelSpecificationBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.ResponseBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.spi.service.contexts.ParameterExpansionContext;
import springfox.documentation.spi.service.contexts.ResponseContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 解析 {@link IntEnum} 的描述信息到 swagger 文档的插件
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
public class IntEnumSwaggerPlugin implements ModelPropertyBuilderPlugin, ExpandedParameterBuilderPlugin, ParameterBuilderPlugin, ResponseBuilderPlugin {

    /**
     * 处理模型中的枚举注释
     * 用于 {@link RequestBody} 和 {@link ResponseBody} 使用的模型
     */
    @Override
    public void apply(ModelPropertyContext context) {
        var definitionOptional = context.getBeanPropertyDefinition();
        if (definitionOptional.isEmpty()) {
            return;
        }
        AnnotatedField type = definitionOptional.get().getField();
        if (!IntEnum.class.isAssignableFrom(type.getRawType())) {
            return;
        }
        var builder = context.getSpecificationBuilder();
        builder.type(new ModelSpecificationBuilder().copyOf(builder.build().getType()).scalarModel(ScalarType.INTEGER).build());
        builder.description(buildDesc(builder.build().getDescription(), type.getRawType()));
    }

    /**
     * 处理参数中的枚举注释
     * 用于方法绑定数据的参数模型
     */
    @Override
    public void apply(ParameterExpansionContext context) {
        ResolvedType type = context.getFieldType();
        if (!type.isInstanceOf(IntEnum.class)) {
            return;
        }
        var builder = context.getRequestParameterBuilder();
        List<String> valueList = Arrays.stream(type.getErasedType().getEnumConstants())
                .map(IntEnum.class::cast).map(IntEnum::val).map(String::valueOf).collect(Collectors.toList());
        builder.query(p -> p.model(c -> c.scalarModel(ScalarType.INTEGER))
                .enumerationFacet(e -> e.allowedValues(new AllowableListValues(valueList, "LIST"))));
        builder.example(new ExampleBuilder().value(valueList.get(0)).build());
        builder.description(buildDesc(builder.build().getDescription(), type.getErasedType()));
    }

    /**
     * 处理参数中的枚举注释
     * 用于方法直接接收的参数
     */
    @Override
    public void apply(ParameterContext parameterContext) {
        ResolvedType type = parameterContext.resolvedMethodParameter().getParameterType();
        if (!type.isInstanceOf(IntEnum.class)) {
            return;
        }
        var builder = parameterContext.requestParameterBuilder();
        builder.query(p -> p.model(c -> c.scalarModel(ScalarType.INTEGER)));
        builder.description(buildDesc(builder.build().getDescription(), type.getErasedType()));
    }

    /**
     * 处理直接返回值的枚举注释
     */
    @Override
    public void apply(ResponseContext responseContext) {
        ResolvedType type = responseContext.getOperationContext().getReturnType();
        if (!type.isInstanceOf(IntEnum.class)) {
            return;
        }
        var builder = responseContext.responseBuilder();
        // 原来的 desc 是 OK, 替换掉
        builder.description(buildDesc(null, type.getErasedType()));
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    /**
     * 拼接模型参数说明文档, 可包含枚举信息
     */
    private String buildDesc(String baseDesc, Class<?> enumClass) {
        StringBuilder noteBuilder = new StringBuilder();
        if (StringUtils.hasText(baseDesc)) {
            noteBuilder.append(baseDesc).append(" ");
        }
        Arrays.stream(enumClass.getEnumConstants()).map(IntEnum.class::cast).forEach(e -> noteBuilder.append(e.val()).append("-").append(e.desc()).append(" "));
        return noteBuilder.toString();
    }
}
