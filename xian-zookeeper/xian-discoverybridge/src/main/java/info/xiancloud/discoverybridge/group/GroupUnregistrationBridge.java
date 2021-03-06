package info.xiancloud.discoverybridge.group;

import info.xiancloud.core.Group;
import info.xiancloud.core.Input;
import info.xiancloud.core.Unit;
import info.xiancloud.core.distribution.GroupProxy;
import info.xiancloud.core.distribution.NodeStatus;
import info.xiancloud.core.distribution.service_discovery.GroupDiscovery;
import info.xiancloud.core.distribution.service_discovery.GroupInstance;
import info.xiancloud.core.message.UnitRequest;
import info.xiancloud.core.message.UnitResponse;
import info.xiancloud.discoverybridge.DiscoveryBridgeGroup;

/**
 * @author happyyangyuan
 */
public class GroupUnregistrationBridge implements Unit {
    @Override
    public Input getInput() {
        return Input.create()
                .add("group", Group.class, "group object", REQUIRED)
                .add("nodeStatus", NodeStatus.class, "node status", REQUIRED);
    }

    @Override
    public Group getGroup() {
        return DiscoveryBridgeGroup.singleton;
    }

    @Override
    public UnitResponse execute(UnitRequest request) {
        GroupProxy groupProxy = request.get("group", GroupProxy.class);
        NodeStatus nodeStatus = request.get("nodeStatus", NodeStatus.class);
        GroupInstance groupInstance = GroupRegistrationBridge.groupInstance(groupProxy, nodeStatus);
        try {
            GroupDiscovery.singleton.unregister(groupInstance);
            return UnitResponse.success();
        } catch (Exception e) {
            return UnitResponse.failure(e, "group注销失败.");
        }
    }
}
