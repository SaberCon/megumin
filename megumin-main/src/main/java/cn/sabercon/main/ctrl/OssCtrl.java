package cn.sabercon.main.ctrl;

import cn.hutool.core.util.IdUtil;
import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.enums.type.FileType;
import cn.sabercon.main.manager.OssManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("file")
    public String upload(MultipartFile file, @NotNull FileType type, @ApiParam("为空时生成 uuid") String filename) {
        if (StringUtils.isEmpty(filename)) {
            filename = IdUtil.simpleUUID();
        }
        return manager.upload(file, type, filename);
    }

}
