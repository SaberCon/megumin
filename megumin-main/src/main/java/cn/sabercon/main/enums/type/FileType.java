package cn.sabercon.main.enums.type;

import lombok.AllArgsConstructor;

/**
 * 文件类型
 *
 * @author SaberCon
 * @since 1.0.0
 */
@AllArgsConstructor
public enum FileType {

    FILE("file", "attachment"),
    IMG("img", "inline"),
    AUDIO("audio", "attachment"),
    VIDEO("video", "attachment"),
    ;
    /**
     * oss 存储目录
     */
    public final String dir;
    /**
     * oss 获取时 Content-Disposition 请求头信息
     */
    public final String contentDisposition;
}
