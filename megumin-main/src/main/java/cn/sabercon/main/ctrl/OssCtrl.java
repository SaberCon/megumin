package cn.sabercon.main.ctrl;

import cn.hutool.core.util.IdUtil;
import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.domain.model.OssToken;
import cn.sabercon.main.enums.type.FileType;
import cn.sabercon.main.manager.OssManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "OSS 相关接口")
@ServiceController("oss")
@RequiredArgsConstructor
public class OssCtrl {

    private final OssManager manager;

    @ApiOperation("上传文件")
    public String upload(@RequestPart MultipartFile file, @RequestParam FileType type, String filename) {
        if (StringUtils.isEmpty(filename)) {
            filename = IdUtil.simpleUUID();
        }
        return manager.upload(file, type, filename);
    }

    @ApiOperation("获取临时 token")
    @GetMapping
    public OssToken getToken(@NotNull FileType type) {
        return manager.getToken(type);
    }

}
