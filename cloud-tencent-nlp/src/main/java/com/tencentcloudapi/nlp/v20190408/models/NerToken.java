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

public class NerToken  extends AbstractModel{

    /**
    * 起始位置
    */
    @SerializedName("BeginOffset")
    @Expose
    private Long BeginOffset;

    /**
    * 长度
    */
    @SerializedName("Length")
    @Expose
    private Long Length;

    /**
    * 命名实体类型
    */
    @SerializedName("Type")
    @Expose
    private String Type;

    /**
    * 基础词
    */
    @SerializedName("Word")
    @Expose
    private String Word;

    /**
     * 获取起始位置
     * @return BeginOffset 起始位置
     */
    public Long getBeginOffset() {
        return this.BeginOffset;
    }

    /**
     * 设置起始位置
     * @param BeginOffset 起始位置
     */
    public void setBeginOffset(Long BeginOffset) {
        this.BeginOffset = BeginOffset;
    }

    /**
     * 获取长度
     * @return Length 长度
     */
    public Long getLength() {
        return this.Length;
    }

    /**
     * 设置长度
     * @param Length 长度
     */
    public void setLength(Long Length) {
        this.Length = Length;
    }

    /**
     * 获取命名实体类型
     * @return Type 命名实体类型
     */
    public String getType() {
        return this.Type;
    }

    /**
     * 设置命名实体类型
     * @param Type 命名实体类型
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * 获取基础词
     * @return Word 基础词
     */
    public String getWord() {
        return this.Word;
    }

    /**
     * 设置基础词
     * @param Word 基础词
     */
    public void setWord(String Word) {
        this.Word = Word;
    }

    /**
     * 内部实现，用户禁止调用
     */
    public void toMap(HashMap<String, String> map, String prefix) {
        this.setParamSimple(map, prefix + "BeginOffset", this.BeginOffset);
        this.setParamSimple(map, prefix + "Length", this.Length);
        this.setParamSimple(map, prefix + "Type", this.Type);
        this.setParamSimple(map, prefix + "Word", this.Word);

    }
}

