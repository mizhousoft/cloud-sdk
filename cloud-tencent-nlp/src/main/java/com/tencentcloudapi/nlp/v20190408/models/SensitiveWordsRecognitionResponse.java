/*
 * Copyright (c) 2017-2018 THL A29 Limited, a Tencent company. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tencentcloudapi.nlp.v20190408.models;

import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.common.AbstractModel;
import com.google.gson.annotations.Expose;
import java.util.HashMap;

public class SensitiveWordsRecognitionResponse  extends AbstractModel{

    /**
    * 敏感词数组
    */
    @SerializedName("SensitiveWords")
    @Expose
    private String [] SensitiveWords;

    /**
    * 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
    */
    @SerializedName("RequestId")
    @Expose
    private String RequestId;

    /**
     * 获取敏感词数组
     * @return SensitiveWords 敏感词数组
     */
    public String [] getSensitiveWords() {
        return this.SensitiveWords;
    }

    /**
     * 设置敏感词数组
     * @param SensitiveWords 敏感词数组
     */
    public void setSensitiveWords(String [] SensitiveWords) {
        this.SensitiveWords = SensitiveWords;
    }

    /**
     * 获取唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     * @return RequestId 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     */
    public String getRequestId() {
        return this.RequestId;
    }

    /**
     * 设置唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     * @param RequestId 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     */
    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }

    /**
     * 内部实现，用户禁止调用
     */
    public void toMap(HashMap<String, String> map, String prefix) {
        this.setParamArraySimple(map, prefix + "SensitiveWords.", this.SensitiveWords);
        this.setParamSimple(map, prefix + "RequestId", this.RequestId);

    }
}

