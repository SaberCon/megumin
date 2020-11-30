package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CommonController;
import cn.sabercon.main.component.OssManager;
import cn.sabercon.main.domain.model.OssToken;
import cn.sabercon.main.enums.type.FileType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "OSS 相关接口")
@CommonController("oss")
@RequiredArgsConstructor
public class OssCtrl {

    private final OssManager manager;

    @PostMapping
    @ApiOperation("上传文件")
    public String upload(@RequestPart MultipartFile file, @RequestParam FileType type, String filename) {
        if (StringUtils.isEmpty(filename)) {
            filename = file.getOriginalFilename();
        }
        return manager.upload(file, type, filename);
    }

    @GetMapping
    @ApiOperation("获取临时 token")
    public OssToken getToken(@NotNull FileType type) {
        return manager.getToken(type);
    }

}
