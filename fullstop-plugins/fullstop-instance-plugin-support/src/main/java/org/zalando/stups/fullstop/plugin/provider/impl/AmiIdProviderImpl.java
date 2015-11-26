/**
 * Copyright (C) 2015 Zalando SE (http://tech.zalando.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zalando.stups.fullstop.plugin.provider.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import org.slf4j.Logger;
import org.zalando.stups.fullstop.plugin.EC2InstanceContext;
import org.zalando.stups.fullstop.plugin.provider.AmiIdProvider;

import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.slf4j.LoggerFactory.getLogger;

public class AmiIdProviderImpl implements AmiIdProvider {

    private static final String IMAGE_ID_JSON_PATH = "$.imageId";

    private final Logger log = getLogger(getClass());

    @Override
    public Optional<String> apply(EC2InstanceContext context) {
        final Optional<String> amiId = readAmiIdFromJson(context);
        if (amiId.isPresent()) {
            return amiId;
        } else {
            return getAmiIdFromEC2Api(context);
        }
    }

    private Optional<String> readAmiIdFromJson(EC2InstanceContext context) {
        try {
            return Optional.ofNullable(JsonPath.read(context.getInstanceJson(), IMAGE_ID_JSON_PATH));
        } catch (JsonPathException ignored) {
            return empty();
        }
    }

    private Optional<String> getAmiIdFromEC2Api(EC2InstanceContext context) {
        final String instanceId = context.getInstanceId();
        try {
            return context.getClient(AmazonEC2Client.class)
                    .describeInstances(new DescribeInstancesRequest().withInstanceIds(instanceId))
                    .getReservations()
                    .stream()
                    .map(Reservation::getInstances)
                    .flatMap(Collection::stream)
                    .filter(i -> i.getInstanceId().equals(instanceId))
                    .map(Instance::getImageId)
                    .findFirst();
        } catch (final AmazonClientException e) {
            log.warn("Could not describe instance " + instanceId, e);
            return empty();
        }
    }
}

