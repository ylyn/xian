package info.xiancloud.plugins.yy.broadcast;

import info.xiancloud.core.Group;
import info.xiancloud.core.Input;
import info.xiancloud.core.Unit;
import info.xiancloud.core.UnitMeta;
import info.xiancloud.core.distribution.LocalNodeManager;
import info.xiancloud.core.message.UnitRequest;
import info.xiancloud.core.message.UnitResponse;
import info.xiancloud.core.test.TestGroup;

/**
 * @author happyyangyuan
 */
public class TestBroadcastUnit implements Unit {
    @Override
    public String getName() {
        return "testBroadcast";
    }

    @Override
    public UnitMeta getMeta() {
        return UnitMeta.create("测试广播型unit").setBroadcast(UnitMeta.Broadcast.create().setAsync(false));
    }

    @Override
    public Input getInput() {
        return new Input();
    }

    @Override
    public UnitResponse execute(UnitRequest msg) {
        return UnitResponse.success(LocalNodeManager.LOCAL_NODE_ID);
    }

    @Override
    public Group getGroup() {
        return TestGroup.singleton;
    }

}
