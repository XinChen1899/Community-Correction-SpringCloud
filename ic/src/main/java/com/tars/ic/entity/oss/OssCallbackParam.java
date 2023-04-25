package com.tars.ic.entity.oss;

import lombok.Data;

@Data
public class OssCallbackParam {
    //  @ApiModelProperty("请求的回调地址")
    private String callbackUrl;
    //  @ApiModelProperty("回调是传入request中的参数")
    private String callbackBody;
    //  @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;
}