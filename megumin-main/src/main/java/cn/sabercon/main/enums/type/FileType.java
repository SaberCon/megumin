package cn.sabercon.main.enums.type;

import cn.sabercon.common.enums.IntEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 文件类型
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum FileType implements IntEnum {

    FILE(1, "文件", "file", "attachment"),
    IMG(2, "图片", "img", "inline"),
    AUDIO(3, "音频", "audio", "attachment"),
    VIDEO(4, "视频", "video", "attachment"),
    ;

    private final int val;
    private final String desc;
    /**
     * oss 存储目录
     */
    private final String dir;
    /**
     * oss 获取时 Content-Disposition 请求头信息
     */
    private final String contentDisposition;
}
