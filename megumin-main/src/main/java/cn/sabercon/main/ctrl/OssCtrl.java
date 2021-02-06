package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CustomController;
import cn.sabercon.main.component.OssHelper;
import cn.sabercon.main.domain.model.OssData;
import cn.sabercon.main.enums.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@CustomController("oss")
@RequiredArgsConstructor
public class OssCtrl {

    private final OssHelper helper;

    @PostMapping
    public String upload(@RequestPart MultipartFile file, FileType type, String filename) {
        if (ObjectUtils.isEmpty(filename)) {
            filename = file.getOriginalFilename();
        }
        return helper.upload(file, type, filename);
    }

    @GetMapping
    public OssData getOssData() {
        return helper.getOssData();
    }

}
