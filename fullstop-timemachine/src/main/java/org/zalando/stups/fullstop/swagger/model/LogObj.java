/**
 * Copyright 2015 Zalando SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.zalando.stups.fullstop.swagger.model;

import java.util.Date;

import com.wordnik.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
public class LogObj  {
  
  private String logType = null;
  private String instanceId = null;
  private String accountId = null;
  private String region = null;
  private Date instanceBootTime = null;
  private String logData = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("logType")
  public String getLogType() {
    return logType;
  }
  public void setLogType(String logType) {
    this.logType = logType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("instanceId")
  public String getInstanceId() {
    return instanceId;
  }
  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("accountId")
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("region")
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("instanceBootTime")
  public Date getInstanceBootTime() {
    return instanceBootTime;
  }
  public void setInstanceBootTime(Date instanceBootTime) {
    this.instanceBootTime = instanceBootTime;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("logData")
  public String getLogData() {
    return logData;
  }
  public void setLogData(String logData) {
    this.logData = logData;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogObj {\n");
    
    sb.append("  logType: ").append(logType).append("\n");
    sb.append("  instanceId: ").append(instanceId).append("\n");
    sb.append("  accountId: ").append(accountId).append("\n");
    sb.append("  region: ").append(region).append("\n");
    sb.append("  instanceBootTime: ").append(instanceBootTime).append("\n");
    sb.append("  logData: ").append(logData).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
